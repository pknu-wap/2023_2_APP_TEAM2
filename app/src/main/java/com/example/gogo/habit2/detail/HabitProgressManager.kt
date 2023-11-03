package com.example.gogo.habit2.detail

import android.widget.TextView

class HabitProgressManager(
    private val currentstatus_number: TextView,
    private val remainingdays_number: TextView
) {
    private var currentStatusNumber: Int = 0
    private var remainingDaysNumber: Int = 66

    init {
        updateTextViews()
    }

    fun getCurrentStatusNumber(): Int {
        return currentStatusNumber
    }

    fun getRemainingDaysNumber(): Int {
        return remainingDaysNumber
    }

    fun updateStatusNum() {
        currentStatusNumber++
        remainingDaysNumber--
        updateTextViews()
    }

    fun resetStatusNum() {
        currentStatusNumber--
        remainingDaysNumber++
        updateTextViews()
    }

    fun allreset() {
        currentStatusNumber= 0
        remainingDaysNumber= 66
    }

    private fun updateTextViews() {
        currentstatus_number.text = currentStatusNumber.toString()
        remainingdays_number.text = remainingDaysNumber.toString()
    }
}

