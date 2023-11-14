package com.example.gogo.habit2.habit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Habit::class], version = 1)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        private var instance: HabitDatabase? = null
        @Synchronized
        fun getInstance(context: Context): HabitDatabase? {
            if (instance == null) {
                synchronized(HabitDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HabitDatabase::class.java,
                        "Habit-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}
