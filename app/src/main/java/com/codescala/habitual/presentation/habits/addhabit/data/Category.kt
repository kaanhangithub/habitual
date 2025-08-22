package com.codescala.habitual.presentation.habits.addhabit.data

import androidx.annotation.DrawableRes
import com.codescala.habitual.R

data class Category(
    val title : String,
    @DrawableRes val icon: Int,
)

val habitCategoryList = listOf(
    Category(
        "Programming",
        R.drawable.ic_habit_programming
    ),
    Category(
        "Reading",
        R.drawable.ic_habit_reading
    ),
    Category(
        "Well-being",
        R.drawable.ic_habit_well_being
    ),
    Category(
        "Fitness",
        R.drawable.ic_exercise_habit_24dp
    ),
    Category(
        "Pet Care",
        R.drawable.ic_habit_pet_care
    ),
    Category(
        "Financial Planning",
        R.drawable.ic_habit_finance_planning
    ),
    Category(
        "Social Connection",
        R.drawable.ic_habit_social_connection
    ),
    Category(
        "Skill Development",
        R.drawable.ic_habit_skill_development
    ),
    Category(
        "Home Organization",
        R.drawable.ic_habit_home_organization
    ),
    Category(
        "Mindfulness",
        R.drawable.ic_habit_mindfulness
    ),
    Category(
        "Hydration",
        R.drawable.ic_habit_hydration
    ),
    Category(
        "Morning Routine",
        R.drawable.ic_habit_morning_routine
    ),
    Category(
        "Evening Routine",
        R.drawable.ic_habit_evening_routine
    ),
    Category(
        "Musical Practice",
        R.drawable.ic_musical_practice
    ),
    Category(
        "Meal Planning",
        R.drawable.ic_habit_meal_planing
    ),
    Category(
        "Sleep",
        R.drawable.ic_habit_evening_routine
    ),
    Category(
        "Learn a Language",
        R.drawable.ic_habit_language
    ),
    Category(
        "Volunteering",
        R.drawable.ic_habit_volunteer
    ),
)