package com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.state

sealed interface YearlyCalendarSideEffect {
    data class ShowError(val message: String) : YearlyCalendarSideEffect
}
