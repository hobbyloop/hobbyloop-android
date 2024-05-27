package com.hobbyloop.feature.reservation.center_detail.monthly_calendar.state

import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.model.DateInfo

data class CurrentMonthCalendarState(
    val dateList: List<DateInfo> = emptyList(),
    val centerScrollOffset: Int = 0,
    val currentCenterIndex: Int = -1,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
