package com.example.habit2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RectangleStateEntity::class], version = 1)
abstract class RectangleDatabase : RoomDatabase() {
    abstract fun rectangleStateDao(): RectangleStateDao
}
