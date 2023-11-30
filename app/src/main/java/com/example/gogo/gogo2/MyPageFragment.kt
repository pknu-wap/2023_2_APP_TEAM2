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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
    private val myPageViewModel: MyPageViewModel by activityViewModels()
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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myPageViewModel = myPageViewModel

        binding.profileImage.setOnClickListener {
            Log.d("MyPageFragment", "Profile image clicked")

            val profileEditDialog = ProfileEditDialogFragment()
            profileEditDialog.setProfileImageListener(object : ProfileEditDialogFragment.ProfileImageListener {
                override fun onProfileImageCaptured(imageUri: Uri) {
                    updateProfileImage(imageUri)
                }
            })
            ProfileEditDialogFragment().show(childFragmentManager, ProfileEditDialogFragment.DIALOG_TAG)

        }

        binding.nickName.setOnClickListener {
            NicknameEditDialog().show(childFragmentManager,NicknameEditDialog.TAG)
        }

        myPageViewModel.goalText.observe(viewLifecycleOwner){
            binding.GoalText.setText(it)
        }

        myPageViewModel.profileImage.observe(viewLifecycleOwner) {
            Glide.with(requireActivity())
                .load(it)
                .error(R.drawable.profile_sky)
                .fallback(R.drawable.profile_sky)
                .into(binding.profileImage)
        }


    }

    fun onHabitItemClicked(isIncrement: Boolean) {
        if (isIncrement) {
            progressEntireUtil.incrementProgress()
        } else {
            progressEntireUtil.resetProgress()
        }
    }

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

    override fun onDestroyView() {

        super.onDestroyView()
        myPageViewModel.updateGoalText(binding.GoalText.text.toString())
    }


}
