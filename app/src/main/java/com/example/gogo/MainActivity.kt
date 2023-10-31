package com.example.gogo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.gogo.databinding.ActivityMainBinding
import com.example.gogo.habit2.habit.data.HabitActivity

class MainActivity : ComponentActivity() {

    private val delayMillis: Long = 3000 // 3초

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // 네비게이션 바를 숨김
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

// 화면이 터치될 때마다 네비게이션 바를 다시 숨김
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // 네비게이션 바가 나타나면 다시 숨김
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
            }
        }
        // 3초 후에 다음 화면으로 이동
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HabitActivity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}