package com.codescala.habitual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codescala.habitual.domain.model.tabs
import com.codescala.habitual.navigation.HabitualNavDisplay
import com.codescala.habitual.navigation.NavManager
import com.codescala.habitual.navigation.Screen
import com.codescala.habitual.navigation.NavTab
import com.codescala.habitual.ui.theme.BackGroundBlack
import com.codescala.habitual.ui.theme.HabitualTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navManager : NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitualTheme {
                Scaffold(
                    bottomBar = {
                        if (navManager.getCurrentScreen() != Screen.Onboarding)
                            BottomNavigationBar(navManager = navManager)
                    },
                    floatingActionButton = {
                        if (navManager.getCurrentScreen() != Screen.Onboarding)
                            FloatingActionButton(
                                onClick = {navManager.navigateToScreen(Screen.AddHabit)},
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = BackGroundBlack
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_plus),
                                    contentDescription = "Add habit"
                                )
                            }
                    },
                    floatingActionButtonPosition = FabPosition.End
                ) { innerPadding->
                    HabitualNavDisplay(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = innerPadding.calculateBottomPadding()
                            ),
                            //.consumeWindowInsets(innerPadding), todo
                        navManager
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navManager: NavManager
) {
    var selectedTab by remember { mutableStateOf<NavTab>(NavTab.HomeTab) }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surface)
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.background
        ) {
            tabs.forEach { tab ->
                NavigationBarItem(
                    selected = selectedTab == tab.id,
                    onClick = {
                        selectedTab = tab.id
                        navManager.navigateToTab(tab.id) },
                    icon = {
                        Icon(
                            painter = painterResource(tab.icon),
                            contentDescription = "Tab",
                        )
                    },
                    label = {
                        Text(
                            text = tab.title,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedIconColor = MaterialTheme.colorScheme.secondary,
                        selectedTextColor =  MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.secondary,
                        indicatorColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    }
}

@Composable
@Preview
private fun BottomNavigationBarPreview() {
    HabitualTheme {
        BottomNavigationBar(navManager = NavManager())
    }
}