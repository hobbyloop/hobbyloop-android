package com.hobbyloop.core.ui.componenet.yearly_calendar.state

import com.hobbyloop.domain.entity.calendar.CalendarMonth
import com.hobbyloop.domain.entity.calendar.DaySelected

data class YearlyCalendarState(
    val calendarYear: List<CalendarMonth>,
    val selectedMonthIndex: Int,
    val selectedMonth: CalendarMonth,
    val selectedDay: DaySelected? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
