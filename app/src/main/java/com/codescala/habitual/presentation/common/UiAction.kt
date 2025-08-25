package com.codescala.habitual.presentation.common

import com.codescala.habitual.presentation.habits.addhabit.data.Category
import com.codescala.habitual.presentation.habits.addhabit.data.FrequencyDay
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.home.data.Day
import java.time.LocalTime

 sealed class UiAction {
    // Edit Habit
    data class EditHabitName(val text: String) : UiAction()
    data class SelectHabitCategory(val category: Category) : UiAction()
    data class SelectScheduleType(val type: Frequency) : UiAction()
    data class SelectTime(val time : LocalTime) : UiAction()
    data class EditHabitNote(val text: String) : UiAction()
    data class ToggleSelectFrequencyDay(val day: FrequencyDay): UiAction()
    object SaveHabit : UiAction()
    object ShowBottomSheet: UiAction()
    object DismissBottomSheet: UiAction()

    // Home
    data class SelectDayForHabits(val day: Day): UiAction()
}