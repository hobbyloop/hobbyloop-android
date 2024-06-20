package com.hobbyloop.core.ui.componenet.monthly_calendar.state

import com.hobbyloop.domain.entity.calendar.DateInfo

data class CurrentMonthCalendarState(
    val dateList: List<DateInfo> = emptyList(),
    val centerScrollOffset: Int = 0,
    val currentCenterIndex: Int = -1,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
