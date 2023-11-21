package com.example.gogo.habit2.habit.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabas하하하e

@Database(
    version = 1,
    entities = [Habit::class]
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
//    abstract fun habitDetailDao() : HabitDetailDao
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
