package com.example.habit2

import android.os.Bundle
import android.view.View
import android.widget.*
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

    private lateinit var habitNameEditText: EditText
    private lateinit var habitDatabase: HabitDatabase
    private lateinit var habitTextView: TextView


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

        habitListView = findViewById(R.id.habitListView)
        addButton = findViewById(R.id.addButton)

        habitNameEditText = findViewById(R.id.habitNameEditText)

        adapter = ArrayAdapter(this, R.layout.list_item,R.id.habitTextView, habitList)
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

        habitListView.setOnItemClickListener { parent, view, position, id ->
            // 해당 항목의 CheckBox 상태를 토글합니다.
            val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
            checkBox.isChecked = !checkBox.isChecked
        }

    }
    fun onRemoveButtonClick(view: View) {
        val parent = view.parent as View
        val listItemLayout = parent.findViewById<LinearLayout>(R.id.listItemLayout) // 부모 레이아웃 찾기
        val habitTextView = parent.findViewById<TextView>(R.id.habitTextView)
        val habitName = habitTextView.text.toString()

        GlobalScope.launch(Dispatchers.IO) {
            habitDatabase.habitDao().deleteHabitByName(habitName)
            runOnUiThread {
                habitList.remove(habitName)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
