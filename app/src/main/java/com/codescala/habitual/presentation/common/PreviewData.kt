package com.codescala.habitual.presentation.common

import com.codescala.habitual.R
import com.codescala.habitual.presentation.habits.addhabit.data.Category
import com.codescala.habitual.presentation.habits.addhabit.data.Day
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.HabitFrequency
import com.codescala.habitual.presentation.habits.addhabit.screenstate.AddHabitState
import java.time.DayOfWeek
import java.time.LocalTime

object PreviewData {

    object AddHabitScreen {
        val screenState = AddHabitState(
            habitName = "Running",
            category = Category(
                title = "Select category",
                icon = R.drawable.ic_launcher_foreground
            ),
            frequency = Frequency.DAILY,
            notes = "Running is great",
            selectedDays = emptySet(),
            selectedTime = LocalTime.now(),
            frequencyList = listOf(
                HabitFrequency(
                    "Daily",
                    Frequency.DAILY
                ),
                HabitFrequency(
                    "Specific Days",
                    Frequency.SPECIFIC_DAYS
                )
            ),
            categoryList = listOf(
                Category(
                    "Work",
                    icon = R.drawable.ic_exercise_habit_24dp
                ),
                Category(
                    "Work",
                    icon = R.drawable.ic_exercise_habit_24dp
                ),
                Category(
                    "Work",
                    icon = R.drawable.ic_exercise_habit_24dp
                ),
                Category(
                    "Work",
                    icon = R.drawable.ic_exercise_habit_24dp
                ),
                Category(
                    "Work",
                    icon = R.drawable.ic_exercise_habit_24dp
                ),
            ),
            daysOfWeekList = listOf(
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
        )
    }
}