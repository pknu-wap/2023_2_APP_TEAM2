package com.example.gogo.habit2.detail.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface RectangleStateDao {
    @Query("SELECT * FROM rectangle_states")
    suspend fun getAllStates(): List<RectangleStateEntity>

    @Update
    fun updateState(rectangleState: RectangleStateEntity)
}
