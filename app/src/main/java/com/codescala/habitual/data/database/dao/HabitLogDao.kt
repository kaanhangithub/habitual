package com.codescala.habitual.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.codescala.habitual.data.model.HabitLog

@Dao
interface HabitLogDao
{
    @Insert
    fun insert(log: HabitLog)

    @Delete
    fun delete(log: HabitLog)
}