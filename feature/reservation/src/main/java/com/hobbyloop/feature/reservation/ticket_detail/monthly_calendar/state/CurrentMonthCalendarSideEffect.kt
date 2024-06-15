package com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state

sealed interface CurrentMonthCalendarSideEffect {
    class ShowError(val message: String) : CurrentMonthCalendarSideEffect
}
