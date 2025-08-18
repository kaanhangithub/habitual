package com.codescala.habitual.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class HabitLog(
    @PrimaryKey val id: Int,
    @ColumnInfo("habit_id") val habitId: Int,
    @ColumnInfo("completion_date") val completionDate: LocalDate
)
