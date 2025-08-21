package com.codescala.habitual.presentation.common

import com.codescala.habitual.presentation.habits.addhabit.data.Day
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import java.time.LocalTime

 sealed class UiAction {
    data class EditHabitName(val text: String) : UiAction()
    data class SelectHabitCategory(val text: String) : UiAction()
    data class SelectScheduleType(val type: Frequency) : UiAction()
    data class SelectTime(val time : LocalTime) : UiAction()
    data class EditHabitNote(val text: String) : UiAction()
    data class ToggleSelectDay(val day: Day): UiAction()
    object SaveHabit : UiAction()
}