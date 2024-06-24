package com.hobbyloop.core.ui.componenet.monthly_calendar.state

import com.hobbyloop.domain.entity.calendar.DateInfo

sealed class CurrentMonthCalendarEvent {
    data class DatesLoaded(val dateList: List<DateInfo>) : CurrentMonthCalendarEvent()
    data class ReservationsLoaded(val dateList: List<DateInfo>) : CurrentMonthCalendarEvent()
    data class LoadFailed(val error: String) : CurrentMonthCalendarEvent()
}
