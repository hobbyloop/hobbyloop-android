package com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.state

import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor

sealed class YearlyCalendarEvent {
    data class ReservationsLoaded(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : YearlyCalendarEvent()
    data class LoadFailed(val error: String) : YearlyCalendarEvent()
}
