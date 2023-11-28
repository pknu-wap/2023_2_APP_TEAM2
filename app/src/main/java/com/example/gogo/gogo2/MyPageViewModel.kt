package com.example.gogo.gogo2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {
    private var _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName

    init {
        _nickName.value = "테스트"
    }

    fun updateNickName(newNickname: String){
        Log.d("MyPageViewModel", "updateNickname: $newNickname")
        _nickName.value = newNickname
    }
}