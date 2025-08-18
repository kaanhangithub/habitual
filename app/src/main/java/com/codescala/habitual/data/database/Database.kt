package com.codescala.habitual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codescala.habitual.data.database.dao.HabitDao
import com.codescala.habitual.data.database.dao.HabitLogDao
import com.codescala.habitual.data.model.HabitLog
import com.codescala.habitual.data.model.Habit
import com.codescala.habitual.data.model.TypeConverter

@Database(entities = [Habit::class, HabitLog::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitLogDao(): HabitLogDao
}