package com.example.habit2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.gogo.R
import com.example.gogo.habit2.habit.data.Habit
import com.example.gogo.habit2.habit.data.HabitDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class mainFragment : Fragment() {
    private val habitList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var habitListView: ListView
    private lateinit var addButton: Button
    private lateinit var removeButton: Button
    private lateinit var habitNameEditText: EditText
    private lateinit var habitDatabase: HabitDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        habitListView = view.findViewById(R.id.habitListView)
        addButton = view.findViewById(R.id.addButton)
        removeButton = view.findViewById(R.id.removeButton)
        habitNameEditText = view.findViewById(R.id.habitNameEditText)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, habitList)
        habitListView.adapter = adapter

        habitDatabase = Room.databaseBuilder(
            requireContext().applicationContext,
            HabitDatabase::class.java,
            "habit-db"
        ).build()

        GlobalScope.launch(Dispatchers.IO) {
            val habits = habitDatabase.habitDao().getAllHabits()
            requireActivity().runOnUiThread {
                habitList.clear()
                habitList.addAll(habits.map { it.name })
                adapter.notifyDataSetChanged()
            }
        }

        addButton.setOnClickListener {
            val habitName = habitNameEditText.text.toString()
            if (habitName.isNotEmpty()) {
                val habit = Habit(name = habitName)
                GlobalScope.launch(Dispatchers.IO) {
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

        return view
    }
}
