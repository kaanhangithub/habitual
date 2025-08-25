package com.codescala.habitual.presentation.habits.addhabit.data

import java.time.DayOfWeek

data class FrequencyDay(
    val name: String,
    val type: DayOfWeek,
    var selected: Boolean = false
)

val daysOfWeekList = listOf(
    FrequencyDay(
        "Mon",
        DayOfWeek.MONDAY
    ),
    FrequencyDay(
        "Tue",
        DayOfWeek.TUESDAY
    ),
    FrequencyDay(
        "Wed",
        DayOfWeek.WEDNESDAY
    ),
    FrequencyDay(
        "Thu",
        DayOfWeek.THURSDAY
    ),
    FrequencyDay(
        "Fri",
        DayOfWeek.FRIDAY
    ),
    FrequencyDay(
        "Sat",
        DayOfWeek.SATURDAY
    ),
    FrequencyDay(
        "Sun",
        DayOfWeek.SUNDAY
    )
)