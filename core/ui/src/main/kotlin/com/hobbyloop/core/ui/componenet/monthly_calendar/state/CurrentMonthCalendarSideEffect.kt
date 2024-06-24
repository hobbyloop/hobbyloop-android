package com.hobbyloop.core.ui.componenet.monthly_calendar.state

sealed interface CurrentMonthCalendarSideEffect {
    class ShowError(val message: String) : CurrentMonthCalendarSideEffect
}
