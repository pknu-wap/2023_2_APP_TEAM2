package com.example.gogo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _fragmentStatus = MutableLiveData<PageType>()
    val fragmentStatus: LiveData<PageType> get() = _fragmentStatus

    init {
        _fragmentStatus.value = PageType.HOME
    }

    fun updateFragmentStatus(pageType: PageType) {
        _fragmentStatus.value = pageType
    }


    enum class PageType {
        HOME,
        MYPAGE
    }
}