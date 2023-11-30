package com.example.gogo.gogo2

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {
    private var _profileImage = MutableLiveData<Uri>()
    private var _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName
    val profileImage : LiveData<Uri> get() = _profileImage


    init {
        _nickName.value = "테스트"
    }

    fun updateNickName(newNickname: String){
        Log.d("MyPageViewModel", "updateNickname: $newNickname")
        _nickName.value = newNickname
    }

    fun updateProfileImage(uri: Uri){
        _profileImage.value = uri
    }
}