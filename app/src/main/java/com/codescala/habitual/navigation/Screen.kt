package com.codescala.habitual.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey {
    @Serializable
    data object Onboarding : Screen()
    @Serializable
    data object Home : Screen()
    @Serializable
    data object MyHabits : Screen()
    @Serializable
    data object Profile : Screen()
    @Serializable
    data object AddHabit : Screen()
    @Serializable
    data class HabitDetails(val habitId: Int): Screen()
}