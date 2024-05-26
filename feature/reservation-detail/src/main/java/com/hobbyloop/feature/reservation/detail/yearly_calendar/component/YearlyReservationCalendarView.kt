package com.hobbyloop.feature.reservation.detail.yearly_calendar.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hobbyloop.feature.reservation.detail.Purple
import com.hobbyloop.feature.reservation.detail.model.ClassInfo
import com.hobbyloop.feature.reservation.detail.model.Instructor
import com.hobbyloop.feature.reservation.detail.yearly_calendar.YearlyCalendarViewModel
import com.hobbyloop.feature.reservation.detail.yearly_calendar.model.DaySelected
import com.hobbyloop.feature.reservation.detail.yearly_calendar.state.YearlyCalendarIntent

@Composable
fun YearlyReservationCalendarView(
    classData: List<Pair<Instructor, List<ClassInfo>>>,
    modifier: Modifier = Modifier,
    yearlyCalendarViewModel: YearlyCalendarViewModel = hiltViewModel(),
    content: @Composable (DaySelected) -> Unit = {}
) {
    val uiState by yearlyCalendarViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(classData) {
        yearlyCalendarViewModel.handleIntent(YearlyCalendarIntent.LoadReservations(classInfo = classData))
    }

    Box(
        modifier = modifier
    ) {
        when {
            uiState.errorMessage != null -> {
                Text(
                    text = uiState.errorMessage.toString(),
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column {
                    val selectedMonth = uiState.selectedMonth
                    val selectedDay = uiState.selectedDay
                    val selectedMonthIndex = uiState.selectedMonthIndex

                    MonthHeader(
                        year = selectedMonth.year.toString(),
                        month = selectedMonth.month.toString(),
                        selectedMonthIndex = selectedMonthIndex,
                        endMonthIndex = uiState.calendarYear.size,
                        onPreviousMonthClick = {
                            yearlyCalendarViewModel.handleIntent(
                                YearlyCalendarIntent.PreviousMonth
                            )
                        },
                        onNextMonthClick = {
                            yearlyCalendarViewModel.handleIntent(
                                YearlyCalendarIntent.NextMonth
                            )
                        }
                    )

                    DaysOfMonth(
                        month = selectedMonth,
                        selectedDay = selectedDay,
                        onDayClicked = { daySelected ->
                            yearlyCalendarViewModel.handleIntent(
                                YearlyCalendarIntent.SelectDay(daySelected = daySelected)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    selectedDay?.let { content(it) }
                }
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(color = Purple, modifier = Modifier.align(Alignment.Center))
        }
    }
}
