package com.codescala.habitual.data.model

import androidx.room.TypeConverter
import java.time.DayOfWeek
import java.time.LocalDate
import kotlin.collections.joinToString

class TypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

    @TypeConverter
    fun fromDayOfWeek(days: Set<DayOfWeek>?): String? {
        return days?.joinToString(",") { it.value.toString() } ?: ""
    }

    @TypeConverter
    fun toDayOfWeek(data: String): Set<DayOfWeek> {
        return if(data.isEmpty()) emptySet()
        else data.split(",").map { DayOfWeek.of(it.toInt()) }.toSet()
    }
}