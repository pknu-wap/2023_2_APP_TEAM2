package com.example.gogo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _fragmentStatus = MutableLiveData<PageType>()
    private var _selectedHabitName = MutableLiveData<String>()
    private var _currentStatus = MutableLiveData<Int>()
    val fragmentStatus: LiveData<PageType> get() = _fragmentStatus
    val selectedHabitName : LiveData<String> get() = _selectedHabitName
    val currentStatus: LiveData<Int> get() = _currentStatus
    init {
        _fragmentStatus.value = PageType.HOME
        _selectedHabitName.value = "ddd"
        _currentStatus.value = 0
    }

    fun updateFragmentStatus(pageType: PageType) {
        _fragmentStatus.value = pageType
    }
    fun updateSelectedHabitName(newName: String) {
        _selectedHabitName.value = newName
    }

    fun updatehabitDays(newStatus: Int) {
        _currentStatus.value = newStatus
    }

    enum class PageType {
        HOME,
        MYPAGE,
        HABIT_DETAIL
    }
}