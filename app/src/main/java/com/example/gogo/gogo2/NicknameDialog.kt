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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.gogo.R
import com.example.gogo.databinding.FragmentDialogNicknameBinding

//import kotlinx.android.synthetic.main.activity_dialog_nickname.*

class NicknameDialog : DialogFragment() {

    private var _binding: FragmentDialogNicknameBinding? = null
    private val binding get() = _binding!!


    private val myPageViewModel: MyPageViewModel by viewModels()

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


        val okBtn: Button = view.findViewById(R.id.okBtn)
        val cancelBtn: Button = view.findViewById(R.id.cancelBtn)
        //val nicknameTextView: TextView = binding.nickNameEdt
//        val nameTextView: TextView = binding.nickNameEdt
//        val name: TextView = binding.nickNameEdt


        val nameEditText: EditText = view.findViewById(R.id.nickNameEdt)
        val savedNickname = getSavedNickname(nameEditText.text.toString())
        nameEditText.setText(savedNickname)

        myPageViewModel.nickName.observe(viewLifecycleOwner, Observer {
            Log.d("NicknameDialog", "LiveData onChanged: $it")
            nameEditText.setText(it)
        })

        okBtn.setOnClickListener {
//            val newNickname = binding.nickNameEdt.text?.toString() ?: ""
            val newNickname = nameEditText.text?.toString() ?: ""
            Log.d("NicknameDialog", "okBtn Clicked: $newNickname")
            myPageViewModel.updateNickName(newNickname)

            saveNickname(newNickname)
            //nicknameTextView.text = newNickname

            dismiss()
        }

        cancelBtn.setOnClickListener {
            dismiss()
        }

    }

    private fun saveNickname(newNickname: String) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("saved_nickname", newNickname)
            apply()
        }
    }

    private fun getSavedNickname(newNickname: String): String {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString("saved_nickname", "") ?: ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myPageViewModel.updateNickName(binding.nickNameEdt.text.toString())
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
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
