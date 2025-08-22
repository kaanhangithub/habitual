package com.codescala.habitual.presentation.habits.addhabit.data

import java.time.DayOfWeek

data class Day(
    val name: String,
    val type: DayOfWeek,
    var selected: Boolean = false
)

val daysOfWeekList = listOf(
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