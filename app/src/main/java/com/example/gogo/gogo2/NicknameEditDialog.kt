package com.example.gogo.gogo2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.gogo.MainViewModel
import com.example.gogo.R
import com.example.gogo.databinding.FragmentDialogNicknameBinding

//import kotlinx.android.synthetic.main.activity_dialog_nickname.*

class NicknameEditDialog : DialogFragment() {

    private var _binding: FragmentDialogNicknameBinding? = null
    private val binding get() = _binding!!


    private val myPageViewModel: MyPageViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogNicknameBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val okBtn: Button = binding.okBtn
        val cancelBtn: Button = binding.cancelBtn
        binding.nickNameEdt.setText(myPageViewModel.nickName.value)

        okBtn.setOnClickListener {
            dismiss()
        }

        cancelBtn.setOnClickListener {
            dismiss()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        myPageViewModel.updateNickName(binding.nickNameEdt.text.toString())
        Log.d("nickname", myPageViewModel.nickName.value.toString())

        _binding = null
    }

//    // 결과를 받아올 때 실행되는 함수 오버라이딩
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // resultCode를 우선 검사
//        if (requestCode == 200 && resultCode == AppCompatActivity.RESULT_OK) {
//            val newNickname = data?.getStringExtra("newNickname")
//
//            // MyPageFragment의 메서드 호출하여 닉네임 변경
//            val myPageFragment = parentFragment as? MyPageFragment
//            myPageFragment?.onNicknameChanged(newNickname)
////            (parentFragment as? MyPageFragment)?.onNicknameChanged(newNickname)
//        }
//    }

    companion object{
        const val TAG = "ProfileEditNickNameDialog"
    }
}
