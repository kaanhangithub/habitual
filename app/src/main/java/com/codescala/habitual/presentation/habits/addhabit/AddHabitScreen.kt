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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.runtime.mutableStateListOf
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
import com.codescala.habitual.R
import com.codescala.habitual.ui.theme.HabitualTheme
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.DayOfWeek

@Composable
fun AddHabitScreen(modifier: Modifier = Modifier) {
    AddHabitUI()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddHabitUI(modifier: Modifier = Modifier) {
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
                            contentDescription = "Home",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        AddHabitSuccessState(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = paddingValues.calculateTopPadding())
        )
    }
}

@Composable
fun AddHabitSuccessState(modifier: Modifier = Modifier) {
    var editText by remember { mutableStateOf("") }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = spacedBy(24.dp),
    ) {
       item {
           TextField(
               value = editText,
               onValueChange = { editText = it },
               modifier = Modifier
                   .fillMaxWidth()
                   .height(56.dp),
               textStyle = MaterialTheme.typography.bodyLarge,
               placeholder = {
                   Text(
                       text = "Habit Name",
                       style = MaterialTheme.typography.bodyLarge,
                       color = MaterialTheme.colorScheme.onBackground
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

        item {
            CategoryPicker()
        }

        item {
            var selectedFrequency by remember { mutableStateOf(Frequency.DAILY) }
            Column {
                Text(
                    text = "Schedule",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = spacedBy(12.dp)
                ){
                    frequencyList.forEach {
                        FilterChip(
                            selected = it.frequency == selectedFrequency,
                            modifier = Modifier.height(44.dp),
                            onClick = {
                                selectedFrequency = it.frequency
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
                    visible = selectedFrequency == Frequency.SPECIFIC_DAYS,
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
                            items(dayOfWeekList.size) { index ->
                                FilterChip(
                                    modifier = Modifier.height(40.dp),
                                    selected = dayOfWeekList[index].selected,
                                    onClick = {
                                        val current = dayOfWeekList[index]
                                        dayOfWeekList[index] = current.copy(selected = !current.selected)
                                    },
                                    label = {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = dayOfWeekList[index].name,
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
                    )
                ) { snappedTime ->

                }
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

enum class Frequency {
    DAILY,
    SPECIFIC_DAYS
}

data class HabitFrequency(
    val title: String,
    val frequency: Frequency
)

val frequencyList = listOf(
    HabitFrequency(
        title = "Daily",
        frequency = Frequency.DAILY
    ),
    HabitFrequency(
        title = "Specific Days",
        frequency = Frequency.SPECIFIC_DAYS
    )
)

data class Day(
    val name: String,
    val type: DayOfWeek,
    var selected: Boolean = false
)

val dayOfWeekList = mutableStateListOf(
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

@Preview
@Composable
private fun AddHabitSuccessStatePreview() {
    HabitualTheme {
        AddHabitScreen()
    }
}