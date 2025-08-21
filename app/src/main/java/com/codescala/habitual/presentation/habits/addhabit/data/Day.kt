package com.codescala.habitual.presentation.habits.addhabit.data

import java.time.DayOfWeek

data class Day(
    val name: String,
    val type: DayOfWeek,
    var selected: Boolean = false
)