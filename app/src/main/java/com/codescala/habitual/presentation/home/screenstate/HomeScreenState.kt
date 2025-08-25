package com.codescala.habitual.presentation.home.screenstate

import com.codescala.habitual.domain.model.Habit
import com.codescala.habitual.presentation.home.data.Day

data class HomeScreenState(
    val selectedDay : Day,
    val days: List<Day>,
    val habits : List<Habit>,
)
