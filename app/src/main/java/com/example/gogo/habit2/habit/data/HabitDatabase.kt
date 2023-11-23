package com.example.gogo.habit2.habit.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    version = 2,
    entities = [Habit::class],
    exportSchema = false
//    autoMigrations = [
//        AutoMigration(
//            from = 1,
//            to = 2
//        )
//    ],
//    exportSchema = true
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
    companion object {
        private var instance: HabitDatabase? = null
        private val MIGRATION_1_TO_2: Migration = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.run {
                    execSQL("ALTER TABLE habits ADD habitDaysCompleted INTEGER NOT NULL DEFAULT 0")
                }
            }
        }

        @Synchronized
        fun getInstance(context: Context): HabitDatabase? {
            if (instance == null) {
                synchronized(HabitDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HabitDatabase::class.java,
                        "Habit-database"
                    ).addMigrations(MIGRATION_1_TO_2).allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}
