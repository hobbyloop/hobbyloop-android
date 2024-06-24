package com.hobbyloop.core.ui.componenet.yearly_calendar.state

sealed interface YearlyCalendarSideEffect {
    data class ShowError(val message: String) : YearlyCalendarSideEffect
}
