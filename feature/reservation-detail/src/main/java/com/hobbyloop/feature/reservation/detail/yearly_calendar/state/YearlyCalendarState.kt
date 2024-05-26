package com.hobbyloop.feature.reservation.detail.yearly_calendar.state

import com.hobbyloop.feature.reservation.detail.yearly_calendar.model.CalendarMonth
import com.hobbyloop.feature.reservation.detail.yearly_calendar.model.DaySelected

data class YearlyCalendarState(
    val calendarYear: List<CalendarMonth>,
    val selectedMonthIndex: Int,
    val selectedMonth: CalendarMonth,
    val selectedDay: DaySelected? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
