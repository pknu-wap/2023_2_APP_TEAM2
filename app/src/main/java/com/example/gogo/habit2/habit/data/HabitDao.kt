package com.example.gogo.habit2.habit.data

import androidx.room.*

@Dao
interface HabitDao {
    @Insert
    fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habits")
    fun getAllHabits(): List<Habit>

    @Query("DELETE FROM habits WHERE name = :name")
    fun deleteHabitByName(name: String)
}
