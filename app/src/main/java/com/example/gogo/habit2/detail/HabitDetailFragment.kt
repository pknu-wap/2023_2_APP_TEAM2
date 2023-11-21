package com.example.gogo.habit2.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gogo.MainViewModel
import com.example.gogo.databinding.FragmentHabitDetailBinding
import com.example.gogo.habit2.habit.data.Habit
import com.example.gogo.habit2.habit.data.HabitDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

class HabitDetailFragment : Fragment() {
    private lateinit var binding: FragmentHabitDetailBinding
    private var habitDatabase: HabitDatabase? = null
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitDetailBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView

        val layoutManager = GridLayoutManager(context,12)
        recyclerView.layoutManager = layoutManager

        binding.habitNameTextView.text = mainViewModel.selectedHabitName.value
        habitDatabase = HabitDatabase.getInstance(requireContext())

//
//        var habitDetail: HabitDetail? = null
//
//        mainViewModel.currentStatus.observe(viewLifecycleOwner) { newStatus ->
//            GlobalScope.launch {
//                val selectedHabitName = mainViewModel.selectedHabitName.value
//                val habitDetail = selectedHabitName?.let {
//                    habitDatabase?.habitDetailDao()?.getHabitDetail(it)
//                }
//                habitDetail?.let {
//                    it.habitDaysCompleted = newStatus
//                    habitDatabase?.habitDetailDao()?.updateHabit(it)
//                }
//            }
//        }




//         ProgressBar 초기화
        val progressBar = binding.progressBar // ProgressBar View 참조
        val progressBarUtil = ProgressBarUtil(progressBar)

        // activity_habit_detail.xml에서 currentstate_number 텍스트뷰 찾기
        val currentstatus_number = binding.currentstatusNumber
        val remainingdays_number = binding.remainingdaysNumber
        val achievementrate_number = binding.achievementrateNumber


        val habitProgressManager = HabitProgressManager(currentstatus_number, remainingdays_number)
        val achievementManager = AchievementManager(achievementrate_number)
        val adapter = RectangleAdapter(progressBarUtil, habitProgressManager, achievementManager)

        recyclerView.adapter = adapter


        return binding.root
    }

}
