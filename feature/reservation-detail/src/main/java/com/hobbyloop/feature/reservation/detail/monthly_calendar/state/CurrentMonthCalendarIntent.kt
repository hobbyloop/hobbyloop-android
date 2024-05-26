package com.hobbyloop.feature.reservation.detail.monthly_calendar.state

import com.hobbyloop.feature.reservation.detail.model.ClassInfo
import com.hobbyloop.feature.reservation.detail.model.Instructor
import com.hobbyloop.feature.reservation.detail.monthly_calendar.model.DateInfo

sealed class CurrentMonthCalendarIntent {
    data object LoadDates : CurrentMonthCalendarIntent()
    data class LoadReservations(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : CurrentMonthCalendarIntent()
    data class UpdateCenterScrollOffset(val offset: Int) : CurrentMonthCalendarIntent()
    data class UpdateCurrentCenterIndex(val index: Int) : CurrentMonthCalendarIntent()
    data class UpdateCurrentCenterIndexWithDates(val dates: List<DateInfo>) : CurrentMonthCalendarIntent()
}
