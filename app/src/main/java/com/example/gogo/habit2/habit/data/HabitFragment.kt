package com.example.gogo.habit2.habit.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gogo.MainViewModel
import com.example.gogo.databinding.FragmentHabitBinding
import com.example.gogo.gogo2.AlarmFunctions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HabitFragment : Fragment() {
    private val habitList = ArrayList<String>()
    private var habitDatabase: HabitDatabase? = null
    private var _binding: FragmentHabitBinding? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var alarmFunctions: AlarmFunctions
    private val binding get() = _binding!!
    private lateinit var adapter: HabitAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val addButton = binding.addButton
        val habitNameEditText = binding.habitNameEditText


        habitDatabase = HabitDatabase.getInstance(requireContext())
        adapter = HabitAdapter(habitList)

        adapter.setOnItemClickListener(object : HabitAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val selectedHabitName = habitList[position]
                mainViewModel.updateSelectedHabitName(selectedHabitName)
                mainViewModel.updateFragmentStatus(MainViewModel.PageType.HABIT_DETAIL)
            }

            override fun onRemoveItemClick(view: View, habitName: String) {
                habitDatabase!!.habitDao().deleteHabitByName(habitName)
                habitList.remove(habitName)
                adapter.notifyDataSetChanged()
            }



        })

        binding.habitList.adapter = adapter
        loadHabitsFromDatabase()


        addButton.setOnClickListener {
            val habitName = habitNameEditText.text.toString()
            //데이터베이스에 습관 추가
            if (habitName.isNotEmpty()) {
                GlobalScope.launch(Dispatchers.IO) {
                    val habit = Habit(name = habitName)
                    habitDatabase?.habitDao()?.insertHabit(habit)
                }
                //추가한 습관 리스트 표시
                habitList.add(habitName)
                adapter.notifyDataSetChanged()
                //습관 입력 창 초기화
                habitNameEditText.text.clear()
            }
        }

        alarmFunctions = AlarmFunctions(requireActivity())
        val time =  "2023-11-30 08:00:00"
        val random = (1..100000)
        val alarmCode = random.random()
        val content = "66일 습관 지키기 잊지 않으셨죠?😉"
        setAlarm(alarmCode, content, time)
    }

    fun setAlarm(alarmCode : Int, content : String, time : String){
        alarmFunctions.callAlarm(time, alarmCode, content)
    }
    private fun loadHabitsFromDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            val habitsFromDatabase = habitDatabase?.habitDao()?.getAllHabits()

            habitsFromDatabase?.let {
                habitList.addAll(it.map { habit -> habit.name })
                GlobalScope.launch(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}