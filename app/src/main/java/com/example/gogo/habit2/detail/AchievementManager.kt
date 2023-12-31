package com.example.gogo.habit2.detail

import android.widget.TextView

class AchievementManager(private val achievementrate_number: TextView) {
    private var progress = 0
    private val maxProgress = 66
    private var achievementrateNumber: Double = 0.0

    init {
        updateTextViews()
    }
    fun updateAchieveRate() {
        progress++
        achievementrateNumber = progress.toDouble() / maxProgress * 100
        updateTextViews()
    }

    fun resetAchieveRate() {
        progress--
        achievementrateNumber = progress.toDouble() / maxProgress * 100
        updateTextViews()
    }

    fun allresetAchieveRate() {
        achievementrateNumber = 0.0
        updateTextViews()
    }

    private fun updateTextViews() {
        val formattedString = String.format("%.1f", achievementrateNumber)
        achievementrate_number.text = formattedString
    }

}
