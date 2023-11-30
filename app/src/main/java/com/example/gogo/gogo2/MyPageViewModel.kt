package com.example.gogo.gogo2

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {
    private var _profileImage = MutableLiveData<Uri>()
    private var _nickName = MutableLiveData<String>()
    private var _goalText = MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName
    val profileImage : LiveData<Uri> get() = _profileImage

    val goalText : LiveData<String> get() = _goalText

    init {
        _nickName.value = "테스트"
        _goalText.value = "목표를 입력해주세요."
    }

    fun updateNickName(newNickname: String){
        Log.d("MyPageViewModel", "updateNickname: $newNickname")
        _nickName.value = newNickname
    }

    fun updateProfileImage(uri: Uri){
        _profileImage.value = uri
    }

    fun updateGoalText(string : String){
        _goalText.value = string
    }
}