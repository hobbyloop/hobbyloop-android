package com.hobbyloop.feature.reservation.center_detail.monthly_calendar.state

import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.model.DateInfo

sealed class CurrentMonthCalendarEvent {
    data class DatesLoaded(val dateList: List<DateInfo>) : CurrentMonthCalendarEvent()
    data class ReservationsLoaded(val dateList: List<DateInfo>) : CurrentMonthCalendarEvent()
    data class LoadFailed(val error: String) : CurrentMonthCalendarEvent()
}
