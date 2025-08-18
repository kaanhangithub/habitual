package com.codescala.habitual.navigation

sealed interface NavTab {
    object HomeTab : NavTab
    object HabitTab: NavTab
    object ProfileTab: NavTab
}