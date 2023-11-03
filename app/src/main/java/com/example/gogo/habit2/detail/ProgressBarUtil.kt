package com.example.gogo.habit2.detail

import android.os.Handler
import android.widget.ProgressBar
import java.util.concurrent.atomic.AtomicBoolean

class ProgressBarUtil(private val progressBar: ProgressBar) {
    private val handler = Handler()
    private var progress = 0
    private val maxProgress = 66
    private val isResetting = AtomicBoolean(false)

    init {
        progressBar.max = maxProgress
    }

    fun incrementProgress() {
        progress++
        progressBar.progress = progress
    }

    fun resetProgress() {
        isResetting.set(true)
        progress--
        progressBar.progress = progress
    }

    fun allresetProgress() {
        progressBar.progress = 0
    }
}
