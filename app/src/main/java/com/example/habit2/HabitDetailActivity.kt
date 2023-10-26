package com.example.habit2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HabitDetailActivity : AppCompatActivity() {
    private lateinit var habitNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_detail) // 새로운 화면의 레이아웃 파일을 설정

        // Intent에서 habitName을 가져옴
        val habitName = intent.getStringExtra("habitName")

        // activity_habit_detail.xml에서의 TextView를 찾음
        habitNameTextView = findViewById(R.id.habitNameTextView)

        // TextView에 habitName을 설정
        habitNameTextView.text = habitName
    }
}
