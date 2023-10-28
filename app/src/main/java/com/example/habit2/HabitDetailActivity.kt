package com.example.habit2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HabitDetailActivity : AppCompatActivity() {
    private lateinit var habitNameTextView: TextView
    private var isDoneState = false  // 상태 변수 (초기 상태: "none")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_detail) // 새로운 화면의 레이아웃 파일을 설정

        // Intent에서 habitName을 가져옴
        val habitName = intent.getStringExtra("habitName")
        // activity_habit_detail.xml에서의 TextView를 찾음
        habitNameTextView = findViewById(R.id.habitNameTextView)
        // TextView에 habitName을 설정
        habitNameTextView.text = habitName

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = GridLayoutManager(this, 10) // 예: 5열로 그리드 표시
        val adapter = RectangleAdapter()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter




    }
}
