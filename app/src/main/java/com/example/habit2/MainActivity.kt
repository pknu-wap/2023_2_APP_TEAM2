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
        // 네비게이션 바를 숨김
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

// 화면이 터치될 때마다 네비게이션 바를 다시 숨김
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // 네비게이션 바가 나타나면 다시 숨김
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
            }
        }

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
