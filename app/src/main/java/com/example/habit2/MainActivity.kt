package com.example.habit2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val habitList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var habitListView: ListView
    private lateinit var addButton: Button
    private lateinit var removeButton: Button
    private lateinit var habitNameEditText: EditText
    private lateinit var habitDatabase: HabitDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        habitListView = findViewById(R.id.habitListView)
        addButton = findViewById(R.id.addButton)
        removeButton = findViewById(R.id.removeButton)
        habitNameEditText = findViewById(R.id.habitNameEditText)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, habitList)
        habitListView.adapter = adapter

        habitDatabase = Room.databaseBuilder(
            applicationContext,
            HabitDatabase::class.java,
            "habit-db"
        ).build()
        GlobalScope.launch(Dispatchers.IO) {
            val habits = habitDatabase.habitDao().getAllHabits()
            runOnUiThread {
                habitList.clear()
                habitList.addAll(habits.map { it.name })
                adapter.notifyDataSetChanged()
            }
        }

        addButton.setOnClickListener {
            val habitName = habitNameEditText.text.toString()
            if (habitName.isNotEmpty()) {
                val habit = Habit(name=habitName)
                GlobalScope.launch(Dispatchers.IO){
                    habitDatabase.habitDao().insertHabit(habit)
                }
                habitList.add(habitName)
                adapter.notifyDataSetChanged()
                habitNameEditText.text.clear()
            }
        }

        removeButton.setOnClickListener {
            val habitName = habitNameEditText.text.toString()
            if (habitName.isNotEmpty() && habitList.contains(habitName)) {
                habitList.remove(habitName)
                adapter.notifyDataSetChanged()
                habitNameEditText.text.clear()
            }
        }
    }
}
