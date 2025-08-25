package com.codescala.habitual.presentation.home.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Day(
   val date: LocalDate
) {
    val monthDay: String get() = date.format(DateTimeFormatter.ofPattern("MMM d"))
    val weekDay: String get() = date.format(DateTimeFormatter.ofPattern("EEE"))
}