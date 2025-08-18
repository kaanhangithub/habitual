package com.codescala.habitual.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDate

@Entity
data class Habit(
    @PrimaryKey val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("start_date") val startDate: LocalDate,
    @ColumnInfo("end_date") val endDate: LocalDate?,
    @ColumnInfo("streak") val streak: Int?,
    @ColumnInfo("schedule_type") val scheduleType: ScheduleType,
    @ColumnInfo("schedule_days") val days: Set<DayOfWeek>?
)

enum class ScheduleType {
    DAILY,
    WEEKLY
}