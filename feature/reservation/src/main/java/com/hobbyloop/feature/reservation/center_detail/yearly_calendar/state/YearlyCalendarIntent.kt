package com.hobbyloop.feature.reservation.center_detail.yearly_calendar.state

import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo
import com.hobbyloop.feature.reservation.center_detail.model.Instructor
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelected

sealed class YearlyCalendarIntent {
    data class LoadReservations(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : YearlyCalendarIntent()
    data class SelectDay(val daySelected: DaySelected) : YearlyCalendarIntent()
    data object NextMonth : YearlyCalendarIntent()
    data object PreviousMonth : YearlyCalendarIntent()
}
