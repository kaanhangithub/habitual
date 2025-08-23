package com.codescala.habitual.presentation.habits.addhabit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codescala.habitual.R
import com.codescala.habitual.presentation.common.PreviewData.AddHabitScreen
import com.codescala.habitual.presentation.common.UiAction
import com.codescala.habitual.presentation.habits.addhabit.data.Category
import com.codescala.habitual.presentation.habits.addhabit.data.Day
import com.codescala.habitual.presentation.habits.addhabit.data.Frequency
import com.codescala.habitual.presentation.habits.addhabit.data.HabitFrequency
import com.codescala.habitual.presentation.habits.addhabit.data.habitCategoryList
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
    val showCategoryBottomSheet by viewModel.showCategoryBottomSheet.collectAsStateWithLifecycle()

    AddHabitUI(
        modifier = modifier,
        state = state,
        uiAction = viewModel::onAction
    )

    if (showCategoryBottomSheet) {
        CategoryBottomSheet(
            state = state,
            uiAction = viewModel::onAction
        )
    }
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
    val focusManager = LocalFocusManager.current
    LazyColumn(
        modifier = modifier
            .pointerInput(Unit) {
            detectTapGestures(onTap = { focusManager.clearFocus() })
        },
        verticalArrangement = spacedBy(24.dp),
    ) {
       item {
           HabitTitle(
               state = state,
               focusManager = focusManager,
               uiAction = uiAction
           )
       }

        item {
            CategoryPicker(
                state = state,
                uiAction = uiAction
            )
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
private fun CategoryPicker(
    modifier: Modifier = Modifier,
    state: AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Category",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyVerticalGrid(
            modifier = Modifier.heightIn(max = 200.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = spacedBy(12.dp),
            verticalArrangement = spacedBy(12.dp)
        ){
            items(
                items = state.categoryList.take(3),
                key = { item -> item.title }
            ) {
                FilterChip(
                    selected = it == state.category,
                    modifier = Modifier.height(58.dp),
                    onClick = {
                        uiAction(UiAction.SelectHabitCategory(it))
                    },
                    label = {
                        Text(
                            text = it.title,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(start = 16.dp),
                            painter = painterResource(id = it.icon),
                            contentDescription = "Category icon",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.background,
                    ),
                    border = if(it != state.category) BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline) else null,
                    shape = RoundedCornerShape(12.dp),
                )
            }

            item {
                FilterChip(
                    selected = false,
                    modifier = Modifier.height(58.dp),
                    onClick = {
                        uiAction(UiAction.ShowBottomSheet)
                    },
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "More",
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
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(12.dp),
                )
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
        modifier = modifier
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
                    border = if(it.frequency != state.frequency) BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline) else null,
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
                    items(
                        items = state.daysOfWeekList,
                        key = { item -> item.name }
                    ) { day ->
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
    focusManager: FocusManager,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryBottomSheet (
    modifier: Modifier = Modifier,
    state: AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = { uiAction(UiAction.DismissBottomSheet) },
        sheetState = sheetState,
        scrimColor = Color.Black.copy(alpha = 0.32f),
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
        BottomSheetContent(
            state = state,
            uiAction = uiAction
        )
    }
}

@Composable
private fun BottomSheetContent(
    modifier: Modifier = Modifier,
    state: AddHabitState,
    uiAction: (UiAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Select Category",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = spacedBy(12.dp),
            verticalArrangement = spacedBy(12.dp)
        ) {
            items(
                items = state.categoryList,
                key = { item -> item.title }
            ) { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            uiAction(UiAction.SelectHabitCategory(category))
                            uiAction(UiAction.DismissBottomSheet)
                                   },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = spacedBy(12.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(start = 16.dp),
                        painter = painterResource(id = category.icon),
                        contentDescription = "Category icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddHabitSuccessStatePreview() {
    HabitualTheme {
        AddHabitUI(
            state = AddHabitScreen.screenState,
            uiAction =  {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetPreview() {
    HabitualTheme { 
        BottomSheetContent(
            state = AddHabitScreen.screenState,
            uiAction = {}
        )
    }
    
}