package com.example.habit2

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
        // 1/66 비율로 초기화
        progress--
        progressBar.progress = progress
    }

    fun allresetProgress() {
        progressBar.progress = 0
    }
}
