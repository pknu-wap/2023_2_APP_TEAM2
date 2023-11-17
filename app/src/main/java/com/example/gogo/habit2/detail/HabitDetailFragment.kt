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

class HabitDetailFragment : Fragment() {
    private lateinit var binding: FragmentHabitDetailBinding

    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitDetailBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView

        val layoutManager = GridLayoutManager(context,10)
        recyclerView.layoutManager = layoutManager

        binding.habitNameTextView.text = mainViewModel.selectedHabitName.value


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
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_habit_detail) // 새로운 화면의 레이아웃 파일을 설정

        // 네비게이션 바를 숨김
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
//        // Intent에서 habitName을 가져옴
//        val habitName = intent.getStringExtra("habitName")
//        // activity_habit_detail.xml에서의 TextView를 찾음
//        habitNameTextView = findViewById(R.id.habitNameTextView)
//        // TextView에 habitName을 설정
//        habitNameTextView.text = habitName
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        val layoutManager = GridLayoutManager(this, 10)
//
//        // ProgressBar 초기화
//        val progressBar = findViewById<ProgressBar>(R.id.progressBar) // ProgressBar View 참조
//        val progressBarUtil = ProgressBarUtil(progressBar)
//        // activity_habit_detail.xml에서 currentstate_number 텍스트뷰 찾기
//        val currentstatus_number = findViewById<TextView>(R.id.currentstatus_number)
//        val remainingdays_number = findViewById<TextView>(R.id.remainingdays_number)
//        val habitProgressManager = HabitProgressManager(currentstatus_number,remainingdays_number)
//        val adapter = RectangleAdapter(progressBarUtil, habitProgressManager)
//
//        //val adapter = RectangleAdapter()
//
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
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
