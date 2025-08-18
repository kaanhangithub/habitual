package com.codescala.habitual.domain.model

import androidx.annotation.DrawableRes

data class Habit(
    val name: String,
    val time: String,
    val isCompleted: Boolean,
    val dayPeriod: DayPeriod,
    @DrawableRes
    val iconId: Int,
    val currentStreak: Int,
)

enum class DayPeriod {
    MORNING,
    AFTERNOON,
    EVENING,
}