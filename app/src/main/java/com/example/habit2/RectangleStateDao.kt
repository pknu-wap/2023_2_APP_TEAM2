package com.example.habit2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RectangleStateDao {
    @Query("SELECT * FROM rectangle_states")
    suspend fun getAllStates(): List<RectangleStateEntity>

    @Update
    fun updateState(rectangleState: RectangleStateEntity)
}
