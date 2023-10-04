package com.example.habit2

import androidx.room.*

@Dao
interface HabitDao {
    @Insert
    fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habits")
    fun getAllHabits(): List<Habit>
}
