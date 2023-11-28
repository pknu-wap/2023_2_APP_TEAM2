package com.example.gogo.gogo2

import android.widget.ProgressBar
class ProgressEntireUtil(private val progressBar: ProgressBar, private val fragment: MyPageFragment) {
    private var progress = 0
    private val maxProgress = 5

    init {
        progressBar.max = maxProgress
    }

    fun incrementProgress() {
        progress++
        progressBar.progress = progress
        fragment.onHabitItemClicked(true)
    }

    fun resetProgress() {
        progress--
        progressBar.progress = progress

        fragment.onHabitItemClicked(false)
    }
}
