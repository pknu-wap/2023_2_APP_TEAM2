package com.example.gogo.habit2.habit.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gogo.MainViewModel
import com.example.gogo.databinding.FragmentHabitBinding


class HabitFragment : Fragment() {
    private val habitList = ArrayList<String>()
    private var habitDatabase: HabitDatabase? = null
    private var _binding : FragmentHabitBinding? = null
    private val mainViewModel : MainViewModel by activityViewModels()
    private val binding get() = _binding!!

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


        val testHabits = listOf("oo","Asdf")
        val adapter = HabitAdapter(testHabits)

        adapter.setOnItemClickListener(object : HabitAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                mainViewModel.updateFragmentStatus(MainViewModel.PageType.HABIT_DETAIL)
            }

        })

        binding.habitList.adapter = adapter

//        // 데이터베이스 초기화
//        habitDatabase = HabitDatabase.getInstance(requireContext())
//
//        //habitList, 데이터베이스에 습관 추가
//        addButton.setOnClickListener {
//            val habitName = habitNameEditText.text.toString()
//            if (habitName.isNotEmpty()) {
//                val habit = Habit(name = habitName)
//                habitDatabase?.habitDao()?.insertHabit(habit)
//
//                habitList.add(habitName)
//                adapter.notifyDataSetChanged()
//                habitNameEditText.text.clear()
//            }
//        }



//        adapter = ArrayAdapter(requireContext(), R.layout.habit_list_item, R.id.habitTextView, habitList)


//        habitListView.adapter = adapter

//        habitDatabase = HabitDatabase.getInstance(requireContext())
//        val habitNames = habitDatabase!!.habitDao().getAllHabits().map { it.name }
//        habitList.addAll(habitNames)
//
//        habitListView.setOnItemClickListener { parent, view, position, id ->
//            // 해당 항목의 CheckBox 상태를 토글합니다.
//            val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
//            checkBox.isChecked = !checkBox.isChecked
//        }
//
//
//        addButton.setOnClickListener {
//            val habitName = habitNameEditText.text.toString()
//            if (habitName.isNotEmpty()) {
//                val habit = Habit(name = habitName)
//
//                habitDatabase!!.habitDao().insertHabit(habit)
//
//                habitList.add(habitName)
//                adapter.notifyDataSetChanged()
//                habitNameEditText.text.clear()
//            }
//        }
//        removeButton.setOnClickListener {
//            val parent = it.parent as View
//            val habitTextView = parent.findViewById<TextView>(R.id.habitTextView)
//            val habitName = habitTextView.text.toString()
//
//            habitDatabase!!.habitDao().deleteHabitByName(habitName)
//
//            habitList.remove(habitName)
//            adapter.notifyDataSetChanged()
//        }
       //return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    fun onRemoveButtonClick(view: View) {
//        val parent = view.parent as View
//        //val listItemLayout = parent.findViewById<LinearLayout>(R.id.listItemLayout) // 부모 레이아웃 찾기
//        val habitTextView = parent.findViewById<TextView>(R.id.habitTextView)
//        val habitName = habitTextView.text.toString()
//
//        habitDatabase!!.habitDao().deleteHabitByName(habitName)
//
//        habitList.remove(habitName)
//        adapter.notifyDataSetChanged()
//
////        GlobalScope.launch(Dispatchers.IO) {
////            habitDatabase.habitDao().deleteHabitByName(habitName)
////            runOnUiThread {
////                habitList.remove(habitName)
////                adapter.notifyDataSetChanged()
////            }
////        }
//    }

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        DataBindingUtil.inflate(())
//
//
//
//        // 네비게이션 바를 숨김
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
//
//        // 화면이 터치될 때마다 네비게이션 바를 다시 숨김
//        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
//            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
//                // 네비게이션 바가 나타나면 다시 숨김
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
//            }
//        }
//
//        habitListView = findViewById(R.id.habitListView)
//        addButton = findViewById(R.id.addButton)
//
//        habitNameEditText = findViewById(R.id.habitNameEditText)
//
//        adapter = ArrayAdapter(this, R.layout.list_item,R.id.habitTextView, habitList)
//        habitListView.adapter = adapter
//
//        habitDatabase = Room.databaseBuilder(
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
//
//        addButton.setOnClickListener {
//            val habitName = habitNameEditText.text.toString()
//            if (habitName.isNotEmpty()) {
//                val habit = Habit(name=habitName)
//                GlobalScope.launch(Dispatchers.IO){
//                    habitDatabase.habitDao().insertHabit(habit)
//                }
//                habitList.add(habitName)
//                adapter.notifyDataSetChanged()
//                habitNameEditText.text.clear()
//            }
//        }
//        habitListView.setOnItemClickListener { parent, view, position, id ->
//            // 해당 항목의 CheckBox 상태를 토글합니다.
//            val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
//            checkBox.isChecked = !checkBox.isChecked
//        }
//
//        //페이지 전환
//        val homeButton = findViewById<ImageButton>(R.id.homeButton)
//        homeButton.setOnClickListener { // 이동하려는 링크 또는 액티비티로 이동하는 코드를 추가
//            // 예를 들어, 웹 페이지로 이동하려면 다음과 같이 설정:
//            val intent = Intent(this, HabitFragment::class.java)
//            startActivity(intent)
//        }
//
//        val myPageButton = findViewById<Button>(R.id.myPageButton)
//        myPageButton.setOnClickListener { // 이동하려는 링크 또는 액티비티로 이동하는 코드를 추가
//            // 예를 들어, 웹 페이지로 이동하려면 다음과 같이 설정:
//            val intent = Intent(this, MyPageActivity::class.java)
//            startActivity(intent)
//        }
//
//
//    }
//    fun onListItemClick(view: View) {
//        val position = habitListView.getPositionForView(view) // 클릭한 뷰의 위치 가져오기
//
//        if (position != ListView.INVALID_POSITION) {
//            // Habit 항목을 클릭했을 때 HabitDetailActivity로 전환
//            val habitName = habitList[position]
//            val intent = Intent(this, HabitDetailActivity::class.java)
//            intent.putExtra("habitName", habitName)
//            startActivity(intent)
//        }
//    }
//
//    fun onRemoveButtonClick(view: View) {
//        val parent = view.parent as View
//        //val listItemLayout = parent.findViewById<LinearLayout>(R.id.listItemLayout) // 부모 레이아웃 찾기
//        val habitTextView = parent.findViewById<TextView>(R.id.habitTextView)
//        val habitName = habitTextView.text.toString()
//
//        GlobalScope.launch(Dispatchers.IO) {
//            habitDatabase.habitDao().deleteHabitByName(habitName)
//            runOnUiThread {
//                habitList.remove(habitName)
//                adapter.notifyDataSetChanged()
//            }
//        }
//    }

}
