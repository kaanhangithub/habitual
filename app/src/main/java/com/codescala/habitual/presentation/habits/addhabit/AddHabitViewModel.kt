package com.codescala.habitual.presentation.habits.addhabit

import androidx.lifecycle.ViewModel
import com.codescala.habitual.R
import com.codescala.habitual.presentation.common.UiAction
import com.codescala.habitual.presentation.common.UiActionHandler
import com.codescala.habitual.presentation.habits.addhabit.data.Category
import com.codescala.habitual.presentation.habits.addhabit.data.Day
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.daysOfWeekList
import com.codescala.habitual.presentation.habits.addhabit.data.frequencyList
import com.codescala.habitual.presentation.habits.addhabit.data.habitCategoryList
import com.codescala.habitual.presentation.habits.addhabit.screenstate.AddHabitState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor() :  ViewModel(), UiActionHandler {

    private val _screenState = MutableStateFlow(
        AddHabitState(
            habitName = "",
            category = null,
            frequency = Frequency.DAILY,
            selectedTime = null,
            notes = "",
            selectedDays = emptySet(),
            frequencyList = frequencyList,
            daysOfWeekList = daysOfWeekList,
            categoryList = habitCategoryList
        )
    )
    val screenState = _screenState.asStateFlow()

    private var _showCategoryBottomSheet = MutableStateFlow(false)
    val showCategoryBottomSheet = _showCategoryBottomSheet.asStateFlow()

    override fun onAction(action: UiAction) {
        when(action) {
            is UiAction.EditHabitName -> {
                _screenState.update { _screenState.value.copy(habitName = action.text) }
            }
            is UiAction.EditHabitNote -> {
                _screenState.update { _screenState.value.copy(notes = action.text) }
            }
            is UiAction.SelectHabitCategory -> {
                _screenState.update { _screenState.value.copy(category = action.category) }
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
            is UiAction.ShowBottomSheet -> {
                _showCategoryBottomSheet.update { true }
            }
            is UiAction.DismissBottomSheet -> {
                _showCategoryBottomSheet.update { false }
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