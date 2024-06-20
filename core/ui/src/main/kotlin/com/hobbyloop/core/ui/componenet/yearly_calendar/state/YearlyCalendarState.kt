package com.hobbyloop.core.ui.componenet.yearly_calendar.state

import com.hobbyloop.data.repository.local.calendar.model.CalendarMonth
import com.hobbyloop.data.repository.local.calendar.model.DaySelected

data class YearlyCalendarState(
    val calendarYear: List<CalendarMonth>,
    val selectedMonthIndex: Int,
    val selectedMonth: CalendarMonth,
    val selectedDay: DaySelected? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
