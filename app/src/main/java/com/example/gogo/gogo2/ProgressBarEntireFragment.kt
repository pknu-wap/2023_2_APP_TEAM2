//package com.example.gogo.gogo2
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.gogo.R
//import com.example.gogo.habit2.detail.ProgressBarUtil
//
//
//class ProgressBarEntireFragment : Fragment() {
//
//    private lateinit var recyclerViewAdapter: ProgressBarUtil
//    private var clickCount = 0
//    private val maxClickCount = 66
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_habit_detail, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        recyclerViewAdapter = ProgressBarUtil(progressBar1)
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
//        recyclerView.adapter = recyclerViewAdapter
//
//        recyclerViewAdapter.setOnClickListener { position ->
//
//            updateProgress()
//        }
//    }
//
//    private fun updateProgress() {
//        clickCount++
//
//        if (clickCount == maxClickCount) {
//            clickCount = 0
//
//            recyclerViewAdapter.incrementProgress()
//        }
//    }
//}