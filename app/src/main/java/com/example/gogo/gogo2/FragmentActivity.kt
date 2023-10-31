//package com.example.gogo2
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.commit
//import com.example.gogo2.databinding.ActivityFragmentBinding
//
//class FragmentActivity : AppCompatActivity() {
//    private val binding by lazy { ActivityFragmentBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = FragmentAc
//        setContentView(binding.root)/
//
//        binding.run {  // 1
//            fragment1Btn.setOnClickListener {
//                setFragment(Fragment1()) // 3
//            }
//            fragment2Btn.setOnClickListener {
//                setFragment(Fragment2())
//            }
//        }
//    }
//
//    private fun setFragment(frag: Fragment) { // 2
//        supportFragmentManager.commit {
//            replace(R.id.frameLayout, frag)
//            setReorderingAllowed(true)
//            addToBackStack("")
//        }
//    }
//}
//
//
//
