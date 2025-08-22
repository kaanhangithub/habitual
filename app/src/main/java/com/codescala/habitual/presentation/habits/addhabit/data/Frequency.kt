package com.codescala.habitual.presentation.habits.addhabit.data

data class HabitFrequency(
    val title: String,
    val frequency: Frequency
)

enum class Frequency {
    DAILY,
    SPECIFIC_DAYS
}

val frequencyList = listOf(
    HabitFrequency(
        title = "Daily",
        frequency = Frequency.DAILY
    ),
    HabitFrequency(
        title = "Specific Days",
        frequency = Frequency.SPECIFIC_DAYS
    )
)