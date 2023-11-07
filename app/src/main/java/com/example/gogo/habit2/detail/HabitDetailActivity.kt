package com.example.gogo.habit2.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gogo.R
import com.example.gogo.habit2.habit.data.HabitFragment
import com.example.gogo2.MyPageActivity

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
        val layoutManager = GridLayoutManager(this, 10)

        // ProgressBar 초기화
        val progressBar = findViewById<ProgressBar>(R.id.progressBar) // ProgressBar View 참조
        val progressBarUtil = ProgressBarUtil(progressBar)
        // activity_habit_detail.xml에서 currentstate_number 텍스트뷰 찾기
        val currentstatus_number = findViewById<TextView>(R.id.currentstatus_number)
        val remainingdays_number = findViewById<TextView>(R.id.remainingdays_number)
        val habitProgressManager = HabitProgressManager(currentstatus_number,remainingdays_number)
        val adapter = RectangleAdapter(progressBarUtil, habitProgressManager)

        //val adapter = RectangleAdapter()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        //페이지 전환
        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        homeButton.setOnClickListener { // 이동하려는 링크 또는 액티비티로 이동하는 코드를 추가
            // 예를 들어, 웹 페이지로 이동하려면 다음과 같이 설정:
            val intent = Intent(this, HabitFragment::class.java)
            startActivity(intent)
        }

        val myPageButton = findViewById<Button>(R.id.myPageButton)
        myPageButton.setOnClickListener { // 이동하려는 링크 또는 액티비티로 이동하는 코드를 추가
            // 예를 들어, 웹 페이지로 이동하려면 다음과 같이 설정:
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }


    }
}
