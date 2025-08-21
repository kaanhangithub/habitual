package com.codescala.habitual.presentation.habits.addhabit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codescala.habitual.R
import com.codescala.habitual.presentation.common.UiAction
import com.codescala.habitual.presentation.habits.addhabit.data.Day
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.HabitFrequency
import com.codescala.habitual.presentation.habits.addhabit.screenstate.AddHabitState
import com.codescala.habitual.ui.theme.BackGroundBlack
import com.codescala.habitual.ui.theme.HabitualTheme
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun AddHabitScreen(
    modifier: Modifier = Modifier,
    viewModel: AddHabitViewModel
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    AddHabitUI(
        modifier = modifier,
        state = state,
        uiAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddHabitUI(
    modifier: Modifier = Modifier,
    state: AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Add Habit",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Back",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        AddHabitContent(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = paddingValues.calculateTopPadding()),
            state = state,
            uiAction = uiAction
        )
    }
}

@Composable
private fun AddHabitContent(
    modifier: Modifier = Modifier,
    state: AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = spacedBy(24.dp),
    ) {
       item {
           HabitTitle(
               state = state,
               uiAction = uiAction
           )
       }

        item {
            CategoryPicker()
        }

        item {
            HabitSchedulePicker(
                state = state,
                uiAction = uiAction
            )
        }

        item {
            HabitNotes(
                state = state,
                uiAction = uiAction
            )
        }
        item {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .height(48.dp),
                onClick = {
                    uiAction(UiAction.SaveHabit)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Save Habit",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = BackGroundBlack,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun CategoryPicker(modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            ),

        ){
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isExpanded = !isExpanded
                    }
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Drop down",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            AnimatedVisibility(
                visible = isExpanded,
                modifier = Modifier,
            ) {
                repeat(5) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "Category",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_plus),
                            contentDescription = "Drop down",
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HabitNotes(
    modifier: Modifier = Modifier,
    state : AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    TextField(
        value = state.notes,
        onValueChange = {
            uiAction(UiAction.EditHabitNote(it))
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        placeholder = {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        shape =  RoundedCornerShape(12.dp),
        isError = false,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorContainerColor = MaterialTheme.colorScheme.surface,
        )
    )
}

@Composable
private fun HabitSchedulePicker(
    modifier: Modifier = Modifier,
    state: AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Schedule",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = spacedBy(12.dp)
        ){
            state.frequencyList.forEach {
                FilterChip(
                    selected = it.frequency == state.frequency,
                    modifier = Modifier.height(44.dp),
                    onClick = {
                        uiAction(UiAction.SelectScheduleType(it.frequency))
                    },
                    label = {
                        Text(
                            text = it.title,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(12.dp),
                )
            }
        }
        AnimatedVisibility(
            visible = state.frequency == Frequency.SPECIFIC_DAYS,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = spacedBy(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    items(state.daysOfWeekList) { day ->
                        FilterChip(
                            modifier = Modifier.height(40.dp),
                            selected = state.selectedDays.contains(day),
                            onClick = {
                                uiAction(UiAction.ToggleSelectDay(day))
                            },
                            label = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = day.name,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                containerColor = MaterialTheme.colorScheme.background,
                            ),
                            border = null,
                            shape = RoundedCornerShape(25.dp),
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        WheelTimePicker(
            size = DpSize(400.dp, 130.dp),
            timeFormat = TimeFormat.AM_PM,
            textColor = MaterialTheme.colorScheme.onBackground,
            textStyle = MaterialTheme.typography.bodyMedium,
            selectorProperties = WheelPickerDefaults.selectorProperties(
                color = MaterialTheme.colorScheme.surface,
                border = null
            ),
            onSnappedTime = {
                uiAction(UiAction.SelectTime(it))
            }
        )
    }
}

@Composable
private fun HabitTitle(
    modifier: Modifier = Modifier,
    state: AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    TextField(
        value = state.habitName,
        onValueChange = {
            uiAction(UiAction.EditHabitName(it)) },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        placeholder = {
            Text(
                text = "Habit Name",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        shape =  RoundedCornerShape(12.dp),
        isError = false,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorContainerColor = MaterialTheme.colorScheme.surface,
        )
    )
}

@Preview
@Composable
private fun AddHabitSuccessStatePreview() {
    HabitualTheme {
        AddHabitUI(
            state = AddHabitState(
                habitName = "Running",
                category = "Sports",
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
            ),
            uiAction =  {}
        )
    }
}