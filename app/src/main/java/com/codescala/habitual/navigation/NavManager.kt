package com.codescala.habitual.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import javax.inject.Inject

class NavManager @Inject constructor() {
    private val stackMap = hashMapOf<NavTab, SnapshotStateList<Screen>>()
    private var currentTab : NavTab? = null
    val currentStack = mutableStateListOf<Screen>(Screen.Onboarding)
    val currentScreen: Screen
        get() = currentStack.lastOrNull() ?: Screen.Onboarding

    fun navigateToScreen(screen: Screen) {
        if (currentScreen != screen) {
            currentStack.add(screen)
            currentTab?.let {
                stackMap[it]?.add(screen)
            }
        }
    }

    fun navigateBack() {
        val lastIndex = currentStack.lastIndex
        if(lastIndex > 0) {
            currentStack.removeAt(lastIndex)
            currentTab?.let {
                stackMap[it]?.removeAt(lastIndex)
            }
        }
    }

    fun navigateToTab(tab: NavTab) {
        if (currentTab != tab) {
            if(!stackMap.containsKey(tab)) {
                val initialScreen = when(tab) {
                    NavTab.HomeTab -> Screen.Home
                    NavTab.HabitTab -> Screen.MyHabits
                    NavTab.ProfileTab -> Screen.Profile
                }
                stackMap[tab] = mutableStateListOf(initialScreen)
            }
            currentTab = tab
            currentStack.clear()
            currentStack.addAll(stackMap[tab] ?: mutableStateListOf(Screen.Home))
        }
    }
}