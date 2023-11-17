package com.example.gogo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _fragmentStatus = MutableLiveData<PageType>()
    private var _selectedHabitName = MutableLiveData<String>()
    val fragmentStatus: LiveData<PageType> get() = _fragmentStatus
    val selectedHabitName : LiveData<String> get() = _selectedHabitName

    init {
        _fragmentStatus.value = PageType.HOME
        _selectedHabitName.value = "물 1L 마시기"
    }

    fun updateFragmentStatus(pageType: PageType) {
        _fragmentStatus.value = pageType
    }


    enum class PageType {
        HOME,
        MYPAGE,
        HABIT_DETAIL
    }
}