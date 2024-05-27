package com.hobbyloop.feature.reservation.center_detail.yearly_calendar

import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo
import com.hobbyloop.feature.reservation.center_detail.model.Instructor
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelected

sealed class CalendarAction {
    data class LoadReservations(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : CalendarAction()
    data class SelectDay(val daySelected: DaySelected) : CalendarAction()
    data object NextMonth : CalendarAction()
    data object PreviousMonth : CalendarAction()
}
