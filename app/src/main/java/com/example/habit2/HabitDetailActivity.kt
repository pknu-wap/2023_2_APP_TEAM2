package com.example.habit2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
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

        // 네비게이션 바를 숨김
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

        // 화면이 터치될 때마다 네비게이션 바를 다시 숨김
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // 네비게이션 바가 나타나면 다시 숨김
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
            }
        }

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

        val homeButton = findViewById<ImageButton>(R.id.homeButton)

        homeButton.setOnClickListener { // 이동하려는 링크 또는 액티비티로 이동하는 코드를 추가
            // 예를 들어, 웹 페이지로 이동하려면 다음과 같이 설정:
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}
