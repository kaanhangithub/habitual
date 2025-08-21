package com.codescala.habitual.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.codescala.habitual.presentation.habits.MyHabitsScreen
import com.codescala.habitual.presentation.habits.addhabit.AddHabitScreen
import com.codescala.habitual.presentation.habits.addhabit.AddHabitViewModel
import com.codescala.habitual.presentation.home.HomeScreen
import com.codescala.habitual.presentation.home.HomeViewModel
import com.codescala.habitual.presentation.onboarding.OnboardingScreen
import com.codescala.habitual.presentation.profile.ProfileScreen

@Composable
fun HabitualNavDisplay(
    modifier: Modifier = Modifier,
    navManager: NavManager,
) {
    NavDisplay(
        modifier = modifier,
        backStack = navManager.currentStack,
        onBack = {
            navManager.navigateBack()
        },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),

        ),
        entryProvider = entryProvider {
            entry<Screen.Home> {
                HomeScreen(viewModel = hiltViewModel<HomeViewModel>())
            }
            entry<Screen.MyHabits> {
                MyHabitsScreen()
            }
            entry<Screen.Profile> {
                ProfileScreen()
            }
            entry<Screen.Onboarding> {
                OnboardingScreen(
                    onGetStarted = {
                        navManager.navigateToTab(NavTab.HomeTab)
                    }
                )
            }
            entry<Screen.AddHabit> {
                AddHabitScreen(viewModel = hiltViewModel<AddHabitViewModel>())
            }
        }
    )
}