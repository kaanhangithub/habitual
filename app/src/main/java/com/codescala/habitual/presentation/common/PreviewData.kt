package com.codescala.habitual.presentation.common

import com.codescala.habitual.R
import com.codescala.habitual.presentation.habits.addhabit.data.Category
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.HabitFrequency
import com.codescala.habitual.presentation.habits.addhabit.data.daysOfWeekList
import com.codescala.habitual.presentation.habits.addhabit.data.habitCategoryList
import com.codescala.habitual.presentation.habits.addhabit.screenstate.AddHabitState
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
            categoryList = habitCategoryList,
            daysOfWeekList = daysOfWeekList
        )
    }
}