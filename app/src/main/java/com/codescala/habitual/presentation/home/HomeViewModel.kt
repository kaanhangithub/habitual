package com.codescala.habitual.presentation.home

import androidx.lifecycle.ViewModel
import com.codescala.habitual.presentation.common.UiAction
import com.codescala.habitual.presentation.common.UiActionHandler
import com.codescala.habitual.presentation.home.data.Day
import com.codescala.habitual.presentation.home.screenstate.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(), UiActionHandler {

    private val _state = MutableStateFlow(
        HomeScreenState(
            selectedDay = Day(LocalDate.now()),
            days = generateDays(LocalDate.now()),
            habits = emptyList()
        )
    )
    val state = _state.asStateFlow()

    private fun generateDays(selectedDay: LocalDate) : List<Day> {
        val startDate = selectedDay.minusWeeks(1)
        val endDate = selectedDay.plusWeeks(1)
        val dayList = mutableListOf<Day>()

        var current = startDate

        while (!current.isAfter(endDate)) {
            dayList.add(Day(current))
            current = current.plusDays(1)
        }
        return  dayList
    }

    override fun onAction(action: UiAction) {
        when(action) {
            is UiAction.SelectDayForHabits -> {
                _state.update { _state.value.copy(selectedDay = action.day) }
            }
            else -> {

            }
        }
    }

}