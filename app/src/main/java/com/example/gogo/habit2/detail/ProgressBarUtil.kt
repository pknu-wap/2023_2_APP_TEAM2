package com.example.gogo.habit2.detail

import android.os.Handler
import android.widget.ProgressBar
import com.example.gogo.habit2.habit.data.Habit
import java.util.Collections.max
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.max

class ProgressBarUtil(private val progressBar: ProgressBar,private val habit: Habit) {
    private val handler = Handler()
    private var progress = 0
    private val maxProgress = 66
    private val isResetting = AtomicBoolean(false)
    var currentStatus = habit.habitDaysCompleted

    init {
        progressBar.max = maxProgress
        updateProgress()
        //currentStatus = progress
    }

    fun incrementProgress() {
        progress++
        progressBar.progress = progress
        currentStatus = progress
        habit.habitDaysCompleted++
        updateProgress()
    }

    fun resetProgress() {
        isResetting.set(true)
        progress--
        progressBar.progress = progress
        currentStatus = progress
        habit.habitDaysCompleted = max(0, habit.habitDaysCompleted - 1)
        updateProgress()
    }

    fun allresetProgress() {
        progress = 0
        progressBar.progress = progress
        currentStatus = progress
        habit.habitDaysCompleted = 0
        updateProgress()
    }

    private fun updateProgress() {
        progressBar.progress = habit.habitDaysCompleted
    }
}
