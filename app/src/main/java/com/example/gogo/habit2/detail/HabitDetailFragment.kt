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
    private val mainViewModel : MainViewModel by activityViewModels()
    //    private lateinit var habitProgressManager: HabitProgressManager
    private lateinit var progressBarUtil: ProgressBarUtil
    private lateinit var habit: Habit
    private val binding get() = _binding!!

    private lateinit var adapter : RectangleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitDetailBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(context,12)
        binding.recyclerView.layoutManager = layoutManager

        habitDatabase = HabitDatabase.getInstance(requireContext())

        habit = habitDatabase!!.habitDao().getHabitByName(mainViewModel.selectedHabitName.value!!)!!

        mainViewModel.updatehabitDays(habit.habitDaysCompleted)

        setUI(habit)


        mainViewModel.currentStatus.observe(viewLifecycleOwner) { currentState ->
            habit.habitDaysCompleted = currentState
            binding.currentstatusNumber.text = currentState.toString()
            binding.remainingdaysNumber.text = (66 - currentState).toString()
            binding.achievementrateNumber.text =
                String.format("%.1f", currentState.toDouble() / 66 * 100)
            binding.progressBar.progress = currentState
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("adapter", mainViewModel.currentStatus.value.toString())
        habit.habitDaysCompleted = mainViewModel.currentStatus.value!!
        habitDatabase?.habitDao()?.updateHabit(habit)

        _binding = null
    }


    private fun setUI(habit: Habit){
        binding.habitNameTextView.text = habit.name
        val progressBar = binding.progressBar // ProgressBar View 참조

        progressBarUtil = ProgressBarUtil(progressBar, habit)

        binding.currentstatusNumber.text = habit.habitDaysCompleted.toString()
        binding.remainingdaysNumber.text = (66-habit.habitDaysCompleted).toString()
        binding.achievementrateNumber.text = String.format("%.1f", habit.habitDaysCompleted.toDouble()/66*100)
        binding.progressBar.progress = habit.habitDaysCompleted

        adapter = RectangleAdapter(progressBarUtil,mainViewModel)

        binding.recyclerView.adapter = adapter
    }


}