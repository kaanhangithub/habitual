package com.codescala.habitual.presentation.habits.addhabit.screenstate

import com.codescala.habitual.presentation.habits.addhabit.data.Category
import com.codescala.habitual.presentation.habits.addhabit.data.FrequencyDay
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.HabitFrequency
import java.time.LocalTime

data class AddHabitState(
    val habitName: String,
    val category: Category?,
    val frequency: Frequency,
    val selectedDays: Set<FrequencyDay>,
    val selectedTime: LocalTime?,
    val notes: String,
    val frequencyList: List<HabitFrequency>,
    val daysOfWeekList: List<FrequencyDay>,
    val categoryList : List<Category>
)
