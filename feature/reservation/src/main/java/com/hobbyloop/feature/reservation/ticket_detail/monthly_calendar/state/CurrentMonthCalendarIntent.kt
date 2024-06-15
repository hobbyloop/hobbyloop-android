package com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state

import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.model.DateInfo

sealed class CurrentMonthCalendarIntent {
    data object LoadDates : CurrentMonthCalendarIntent()
    data class LoadReservations(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : CurrentMonthCalendarIntent()
    data class UpdateCenterScrollOffset(val centerScrollOffset: Int) : CurrentMonthCalendarIntent()
    data class UpdateCurrentCenterIndex(val centerIndex: Int) : CurrentMonthCalendarIntent()
    data class UpdateCurrentCenterIndexWithDates(val dates: List<DateInfo>) : CurrentMonthCalendarIntent()
}
