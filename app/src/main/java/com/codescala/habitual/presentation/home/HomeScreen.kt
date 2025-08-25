package com.codescala.habitual.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codescala.habitual.R
import com.codescala.habitual.domain.model.DayPeriod
import com.codescala.habitual.domain.model.Habit
import com.codescala.habitual.navigation.Screen
import com.codescala.habitual.presentation.common.UiAction
import com.codescala.habitual.presentation.home.screenstate.HomeScreenState
import com.codescala.habitual.ui.theme.BackGroundBlack
import com.codescala.habitual.ui.theme.HabitualTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenUI(state = state, uiAction = viewModel::onAction)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    uiAction: (UiAction) -> Unit,
    state: HomeScreenState
) {
    Scaffold(
        topBar = {
        CenterAlignedTopAppBar(
            title = {
                    Text(
                        text = "Habit Tracker",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_calander),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Home",
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )
    },
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
                .background(MaterialTheme.colorScheme.background)
        ) {
            val pagerState =  rememberPagerState(
                initialPage = 0,
                pageCount = {10}
            )
            DateScrollableTabRow(state = state, uiAction = uiAction)
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState
            ) { pageIndex ->
                HomePagerContent()
            }

        }
    }

}

@Composable
fun HomePagerContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Daily Habits",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Today's quote
        item {
            Text(
                text = "\"The secret of your future is hidden in your daily routine.\" - Olivia Hayes",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            HabitList(
                title = "Morning",
                habits = previewHabits.filter { it.dayPeriod == DayPeriod.MORNING }
            )
            HabitList(
                title = "Afternoon",
                habits = previewHabits.filter { it.dayPeriod == DayPeriod.AFTERNOON }
            )
            HabitList(
                title = "Evening",
                habits = previewHabits.filter { it.dayPeriod == DayPeriod.EVENING }
            )
            Spacer(modifier = Modifier.height(100.dp))
        }

    }
}

@Composable
private fun HabitList(
    modifier: Modifier = Modifier,
    title: String,
    habits: List<Habit>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Left,
        )

        habits.forEach {
            HabitItem(
                habit = it
            )
        }
    }
}

@Composable
fun HabitItem(
    modifier: Modifier = Modifier,
    habit: Habit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = habit.iconId),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = habit.name,
            )
        }
        Spacer(
            modifier = Modifier.width(16.dp)
        )
        Column {
            Text(
                text = habit.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textDecoration = if (habit.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = habit.time,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Spacer(modifier = Modifier.weight(1.8f))
        Checkbox(
            modifier = Modifier.weight(0.2f),
            checked = habit.isCompleted,
            onCheckedChange = {},
            colors =  CheckboxDefaults.colors(
                uncheckedColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Composable
private fun DateScrollableTabRow(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    uiAction: (UiAction) -> Unit,
) {
    ScrollableTabRow(
        modifier = modifier.fillMaxWidth(),
        edgePadding = 0.dp,
        selectedTabIndex = 0,
        containerColor = MaterialTheme.colorScheme.background,
        indicator = {},
        divider = {},
        tabs = {
            state.days.forEach {
                Tab(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .background(
                            color = if (it == state.selectedDay)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    text = {
                        Column(
                            verticalArrangement = spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = it.monthDay, style = MaterialTheme.typography.bodyMedium)
                            Text(text = it.weekDay, style = MaterialTheme.typography.bodyMedium)
                        }

                    },
                    selected = it == state.selectedDay,
                    selectedContentColor = MaterialTheme.colorScheme.onBackground,
                    unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                    onClick = {
                        uiAction(UiAction.SelectDayForHabits(it))
                    },
                )
            }
//
//            listOf("Aug 3", "Aug 4", "Aug 5", "Aug 6", "Aug 7", "Aug 8", "Aug 9")
//                .forEach {
//                    Tab(
//                        modifier = Modifier
//                            .padding(horizontal = 6.dp)
//                            .background(
//                                color = MaterialTheme.colorScheme.surface,
//                                shape = RoundedCornerShape(8.dp)
//                            ),
//                        text = {
//                            Column(
//                                verticalArrangement = Arrangement.Center,
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ) {
//                                Text(it)
//                                Text("Tue")
//                            }
//
//                        },
//                        selected = false,
//                        selectedContentColor = MaterialTheme.colorScheme.outline,
//                        unselectedContentColor = MaterialTheme.colorScheme.onBackground,
//                        onClick = {},
//                    )
//                }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFCFAF7)
@Composable
private fun HomeScreenPreview() {
    HabitualTheme {
//        Surface(
//            //modifier = Modifier.fillMaxSize(),
//        ) {
           // HomeScreen()
        //}

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFCFAF7)
@Composable
private fun HabitListPrev() {
    HabitualTheme {
        HabitList(
            title = "Morning",
            habits = previewHabits
        )
    }
    
}

val previewHabits = listOf(
    // 🌅 Morning
    Habit(
        name = "Drink Water",
        time = "07:00 AM",
        isCompleted = true,
        dayPeriod = DayPeriod.MORNING,
        iconId = R.drawable.ic_exercise_habit_24dp, // Replace with actual drawable
        currentStreak = 5
    ),
    Habit(
        name = "Morning Walk",
        time = "06:30 AM",
        isCompleted = false,
        dayPeriod = DayPeriod.MORNING,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 12
    ),
    Habit(
        name = "Meditate",
        time = "08:00 AM",
        isCompleted = false,
        dayPeriod = DayPeriod.MORNING,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 3
    ),

    // 🌤️ Afternoon
    Habit(
        name = "Stretching",
        time = "12:30 PM",
        isCompleted = false,
        dayPeriod = DayPeriod.AFTERNOON,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 1
    ),
    Habit(
        name = "Read Book",
        time = "01:00 PM",
        isCompleted = true,
        dayPeriod = DayPeriod.AFTERNOON,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 4
    ),
    Habit(
        name = "Eat Fruit",
        time = "02:15 PM",
        isCompleted = false,
        dayPeriod = DayPeriod.AFTERNOON,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 9
    ),

    // 🌇 Evening
    Habit(
        name = "Evening Jog",
        time = "06:00 PM",
        isCompleted = true,
        dayPeriod = DayPeriod.EVENING,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 10
    ),
    Habit(
        name = "Journal",
        time = "08:00 PM",
        isCompleted = false,
        dayPeriod = DayPeriod.EVENING,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 2
    ),
    Habit(
        name = "Sleep by 10",
        time = "10:00 PM",
        isCompleted = false,
        dayPeriod = DayPeriod.EVENING,
        iconId = R.drawable.ic_exercise_habit_24dp,
        currentStreak = 7
    )
)
