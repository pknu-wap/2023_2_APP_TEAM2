package com.example.gogo.gogo2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gogo.R

//import kotlinx.android.synthetic.main.activity_dialog_nickname.*

class EditNicknameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_nickname, container, false)

        view.findViewById<View>(R.id.okBtn).setOnClickListener {
            val resultIntent = Intent()

            val input = view.findViewById<EditText>(R.id.nickNameEdt).text.toString()
            resultIntent.putExtra("newNickname", input)
            requireActivity().setResult(Activity.RESULT_OK, resultIntent)

            requireActivity().finish()
        }

        return view



//    // 닉네임 수정 후 완료버튼 눌렀을 때
//    okBtn.setOnClickListener {
//
//        val resultsIntent = Intent()
//
//        val input = nickNameEdt.text.toString()
//        resultsIntent.putExtra("newNickname", input)
//        setResult(Activity.RESULT_OK, resultIntent)
//
//        finish()
//    }


    }

}
