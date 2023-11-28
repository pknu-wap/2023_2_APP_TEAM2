package com.example.gogo.gogo2

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.gogo.R
import com.example.gogo.databinding.FragmentMypageBinding

//interface HabitItemClickListener {
//    fun onHabitItemClicked(isIncrement: Boolean)
//}

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMypageBinding
    private lateinit var myPageViewModel: MyPageViewModel
    private lateinit var name: TextView
    private lateinit var progressEntireUtil: ProgressEntireUtil
    private val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123

    companion object {
        val TAG: String = MyPageFragment::class.java.simpleName
        private const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }
//        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
//        name = binding.name
//        name = view.findViewById(R.id.name)
//        binding = FragmentMypageBinding.inflate(inflater, container, false)
//
//        binding.name.text = myPageViewModel.nickName.value
//        myPageViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)
//        myPageViewModel.nickName.observe(viewLifecycleOwner, Observer {

//            name.text = it
//        }
//    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = binding.nickName

        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        myPageViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)

        myPageViewModel.nickName.observe(viewLifecycleOwner, Observer {
            name.text = it
        })

        binding.profileImage.setOnClickListener {
            Log.d("MyPageFragment", "Profile image clicked")

            val profileEditDialog = ProfileEditDialogFragment()
            profileEditDialog.setProfileImageListener(object : ProfileEditDialogFragment.ProfileImageListener {
                override fun onProfileImageCaptured(imageUri: Uri) {
                    updateProfileImage(imageUri)
                }
            })
            ProfileEditDialogFragment().show(childFragmentManager, ProfileEditDialogFragment.TAG)

        }

        binding.nickName.setOnClickListener {
            NicknameDialog().show(childFragmentManager,NicknameDialog.TAG)
        }

        val progressBar1: ProgressBar = view.findViewById(R.id.progressBar1)
        progressEntireUtil = ProgressEntireUtil(progressBar1, this)



//            if (ContextCompat.checkSelfPermission(
//                    requireActivity(),
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
//                )
//            } else {
//

//                dialog.setEditNicknameListener(object : EditNicknameActivity.EditNicknameListener {
//                    override fun onOkButtonClicked(newNickname: String) {
//                        val resultIntent = Intent()
//                        resultIntent.putExtra("newNickname", newNickname)
//                        setResult(Activity.RESULT_OK, resultIntent)
//                        finish()
//                    }
//                })
//                dialog.show(supportFragmentManager, "CustomDialog")
//            }
    }

    fun onHabitItemClicked(isIncrement: Boolean) {
        if (isIncrement) {
            progressEntireUtil.incrementProgress()
        } else {
            progressEntireUtil.resetProgress()
        }
    }


//        binding.okBtn.setOnClickListener {
//            Toast.makeText(this, "Ok button clicked", Toast.LENGTH_SHORT).show()
//            // 닉네임 수정 후 완료버튼 눌렀을 때
////            binding.okBtn.setOnClickListener {
////                val resultIntent = Intent()
////
////                val input = NicknameDialog.text.toString()
////                resultIntent.putExtra("newNickname", input)
////                setResult(Activity.RESULT_OK, resultIntent)
////
////                finish()
////            }
//        }


//        return binding.root
//    }

    private fun isNotGranted() =
        ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED

    fun updateProfileImage(imageUri: Uri) {
        try {
            val requestOptions = RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
            Glide.with(requireContext())
                .load(imageUri)
                .apply(requestOptions)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.profileImage)
    } catch (e: Exception) {
        e.printStackTrace()

        }    }


    fun onNicknameChanged(newNickname: String) {
        if (!newNickname.isNullOrBlank()) {
            Toast.makeText(requireContext(), "닉네임이 변경되었습니다. $newNickname", Toast.LENGTH_SHORT)
                .show()
        }
        myPageViewModel.updateNickName(newNickname?: "")
    }


}
