package com.example.gogo.habit2.detail

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

class HabitDetailFragment : Fragment() {
    private var _binding: FragmentHabitDetailBinding? = null
    private var habitDatabase: HabitDatabase? = null
    private lateinit var habit: Habit
    private val mainViewModel : MainViewModel by activityViewModels()
//    private lateinit var habitProgressManager: HabitProgressManager
    private lateinit var progressBarUtil: ProgressBarUtil
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitDetailBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerView

        val layoutManager = GridLayoutManager(context,12)
        recyclerView.layoutManager = layoutManager

        habitDatabase = HabitDatabase.getInstance(requireContext())

        mainViewModel.selectedHabitName.observe(viewLifecycleOwner) {habitName ->
            binding.habitNameTextView.text = habitName
        }
        val progressBar = binding.progressBar // ProgressBar View 참조
        habit = Habit(name = "YourHabitName")
        val progressBarUtil = ProgressBarUtil(progressBar, habit)


        Log.d("HabitDetailFragment", "currentStatus: ${mainViewModel.currentStatus.value}")
        mainViewModel.currentStatus.observe(viewLifecycleOwner) {currentState->
            Log.d("HabitDetailFragment", "currentStatus: ${mainViewModel.currentStatus.value}")
            binding.currentstatusNumber.text = currentState.toString()
            binding.remainingdaysNumber.text = (66-currentState).toString()
            binding.achievementrateNumber.text = String.format("%.1f", currentState.toDouble()/66*100)
            binding.progressBar.progress = currentState
        }

//         ProgressBar 초기화


        // activity_habit_detail.xml에서 currentstate_number 텍스트뷰 찾기
        val currentstatus_number = binding.currentstatusNumber
        val remainingdays_number = binding.remainingdaysNumber
        val achievementrate_number = binding.achievementrateNumber


        val habitProgressManager = HabitProgressManager(currentstatus_number, remainingdays_number)
        val achievementManager = AchievementManager(achievementrate_number)
        val adapter = RectangleAdapter(progressBarUtil, habitProgressManager, achievementManager, mainViewModel)

        recyclerView.adapter = adapter


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
