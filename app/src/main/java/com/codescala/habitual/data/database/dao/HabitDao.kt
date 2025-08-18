package com.codescala.habitual.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codescala.habitual.data.model.Habit
import java.time.LocalDate

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getAll(): List<Habit>

    @Query("""
        SELECT h.* 
        FROM Habit h
        INNER JOIN HabitLog l ON h.id = l.habit_id
        WHERE l.completion_date = :date
    """)
    fun getCompletedHabitsByDate(date: LocalDate): List<Habit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(habit: Habit)

    @Delete
    fun delete(habit: Habit)
}