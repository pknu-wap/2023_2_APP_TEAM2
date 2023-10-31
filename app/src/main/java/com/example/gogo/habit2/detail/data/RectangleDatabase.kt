package com.example.gogo.habit2.detail.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RectangleStateEntity::class], version = 1)
abstract class RectangleDatabase : RoomDatabase() {
    abstract fun rectangleStateDao(): RectangleStateDao
}
