package com.example.gogo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.gogo.databinding.ActivityMainBinding
import com.example.gogo.habit2.detail.HabitDetailFragment
import com.example.gogo.habit2.habit.data.HabitDatabase
import com.example.gogo.habit2.habit.data.HabitFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val delayMillis: Long = 3000 // 3초

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationView.setBackgroundColor(resources.getColor(R.color.NavigationColor))
        mainViewModel.fragmentStatus.observe(this) {
            when (it) {
                MainViewModel.PageType.HOME -> {
                    loadFragment(HabitFragment())
                }

                MainViewModel.PageType.MYPAGE -> {

                }
                MainViewModel.PageType.HABIT_DETAIL -> {
                    loadFragment(HabitDetailFragment())
                }
            }
        }

        setNavigationItemClickListener()
        hideActionBar()
//        val habitDatabase = Room.databaseBuilder(
//            applicationContext,
//            HabitDatabase::class.java,
//            "habit-db"
//        ).build()
//        GlobalScope.launch(Dispatchers.IO) {
//            val habits = habitDatabase.habitDao().getAllHabits()
//            runOnUiThread {
//                habitList.clear()
//                habitList.addAll(habits.map { it.name })
//                adapter.notifyDataSetChanged()
//            }
//        }

        // 네비게이션 바를 숨김
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
//
//// 화면이 터치될 때마다 네비게이션 바를 다시 숨김
//        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
//            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
//                // 네비게이션 바가 나타나면 다시 숨김
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
//            }
//        }
//        // 3초 후에 다음 화면으로 이동
//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, HabitActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, delayMillis)
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setNavigationItemClickListener(){
        binding.navigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeButton ->{
                    mainViewModel.updateFragmentStatus(MainViewModel.PageType.HOME)
                    return@setOnItemSelectedListener true
                }
                R.id.myPageButton -> {
                    mainViewModel.updateFragmentStatus(MainViewModel.PageType.MYPAGE)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }


    private fun hideActionBar(){
        supportActionBar?.hide()
    }
}