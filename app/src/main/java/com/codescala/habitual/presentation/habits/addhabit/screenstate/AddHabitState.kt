package com.codescala.habitual.presentation.habits.addhabit.screenstate

import com.codescala.habitual.presentation.habits.addhabit.data.Day
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.HabitFrequency
import java.time.LocalTime

data class AddHabitState(
    val habitName: String,
    val category: String,
    val frequency: Frequency,
    val selectedDays: Set<Day>,
    val selectedTime: LocalTime?,
    val notes: String,
    val frequencyList: List<HabitFrequency>,
    val daysOfWeekList: List<Day>,
)
