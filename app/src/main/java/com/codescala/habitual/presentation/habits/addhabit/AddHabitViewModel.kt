package com.codescala.habitual.presentation.habits.addhabit

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.codescala.habitual.presentation.common.UiAction
import com.codescala.habitual.presentation.common.UiActionHandler
import com.codescala.habitual.presentation.habits.addhabit.data.Day
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.HabitFrequency
import com.codescala.habitual.presentation.habits.addhabit.screenstate.AddHabitState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor() :  ViewModel(), UiActionHandler {
    private val frequencyList = listOf(
        HabitFrequency(
            title = "Daily",
            frequency = Frequency.DAILY
        ),
        HabitFrequency(
            title = "Specific Days",
            frequency = Frequency.SPECIFIC_DAYS
        )
    )

    private val daysOfWeekList = listOf(
        Day(
            "Mon",
            DayOfWeek.MONDAY
        ),
        Day(
            "Tue",
            DayOfWeek.TUESDAY
        ),
        Day(
            "Wed",
            DayOfWeek.WEDNESDAY
        ),
        Day(
            "Thu",
            DayOfWeek.THURSDAY
        ),
        Day(
            "Fri",
            DayOfWeek.FRIDAY
        ),
        Day(
            "Sat",
            DayOfWeek.SATURDAY
        ),
        Day(
            "Sun",
            DayOfWeek.SUNDAY
        )
    )

    private val _screenState = MutableStateFlow(
        AddHabitState(
            habitName = "",
            category =  "",
            frequency = Frequency.DAILY,
            selectedTime = null,
            notes = "",
            selectedDays = emptySet(),
            frequencyList = frequencyList,
            daysOfWeekList = daysOfWeekList
        )
    )
    val screenState = _screenState.asStateFlow()



    override fun onAction(action: UiAction) {
        when(action) {
            is UiAction.EditHabitName -> {
                _screenState.update { _screenState.value.copy(habitName = action.text) }
            }
            is UiAction.EditHabitNote -> {
                _screenState.update { _screenState.value.copy(notes = action.text) }
            }
            is UiAction.SelectHabitCategory -> {
                _screenState.update { _screenState.value.copy(category = action.text) }
            }
            is UiAction.SelectScheduleType -> {
                _screenState.update { _screenState.value.copy(frequency = action.type) }
            }
            is UiAction.SelectTime -> {
                _screenState.update { _screenState.value.copy(selectedTime = action.time) }
            }
            is UiAction.ToggleSelectDay -> {
                toggleSelectedDays(action.day)
            }
            is UiAction.SaveHabit -> {

            }
            else -> {

            }
        }
    }

    private fun toggleSelectedDays(day: Day) {
        val days = _screenState.value.selectedDays.toMutableSet()
        if(days.contains(day)) {
            days.remove(day)
        } else {
            days.add(day)
        }
        _screenState.update { _screenState.value.copy(selectedDays = days.toSet()) }
    }
}