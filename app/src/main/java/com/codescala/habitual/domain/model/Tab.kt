package com.codescala.habitual.domain.model

import androidx.annotation.DrawableRes
import com.codescala.habitual.R
import com.codescala.habitual.navigation.NavTab

data class Tab(
    val id: NavTab,
    val title : String,
    @DrawableRes val icon: Int,
)

val tabs = listOf(
    Tab(title = "Dashboard", id = NavTab.HomeTab, icon = R.drawable.ic_exercise_habit_24dp),
    Tab(title = "My Habits", id = NavTab.HabitTab, icon = R.drawable.ic_exercise_habit_24dp),
    Tab(title = "Profile", id = NavTab.ProfileTab, icon = R.drawable.ic_exercise_habit_24dp),
)