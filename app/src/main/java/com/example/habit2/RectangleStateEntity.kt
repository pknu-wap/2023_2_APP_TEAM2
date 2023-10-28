package com.example.habit2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rectangle_states")
data class RectangleStateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "is_done") val isDone: Boolean
)
