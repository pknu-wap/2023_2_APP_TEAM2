package com.example.gogo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.gogo.databinding.ActivityMainBinding
import com.example.gogo.gogo2.MyPageFragment
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
                    loadFragment(MyPageFragment())
                }
                MainViewModel.PageType.HABIT_DETAIL -> {
                    loadFragment(HabitDetailFragment())
                }
            }
        }
        setNavigationItemClickListener()
        hideActionBar()

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