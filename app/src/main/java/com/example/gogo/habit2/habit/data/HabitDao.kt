package com.example.gogo.habit2.habit.data

import androidx.room.*

@Dao
interface HabitDao {
    @Insert
    fun insertHabit(habit: Habit)

    @Query("SELECT * FROM habits")
    fun getAllHabits(): List<Habit>

    @Transaction
    @Query("DELETE FROM habits WHERE name = :name")
    fun deleteHabitByName(name: String)

    @Update
    fun updateHabit(habit: Habit)
//
//    @Query("SELECT * FROM habits WHERE name = :habitName")
//    fun getHabitDetail(habitName: String)

}

