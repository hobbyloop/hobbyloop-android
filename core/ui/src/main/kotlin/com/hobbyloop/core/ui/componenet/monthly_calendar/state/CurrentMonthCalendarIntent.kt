package com.hobbyloop.core.ui.componenet.monthly_calendar.state

import com.hobbyloop.domain.entity.calendar.DateInfo
import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor

sealed class CurrentMonthCalendarIntent {
    data object LoadDates : CurrentMonthCalendarIntent()
    data class LoadReservations(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : CurrentMonthCalendarIntent()
    data class UpdateCenterScrollOffset(val centerScrollOffset: Int) : CurrentMonthCalendarIntent()
    data class UpdateCurrentCenterIndex(val centerIndex: Int) : CurrentMonthCalendarIntent()
    data class UpdateCurrentCenterIndexWithDates(val dates: List<DateInfo>) : CurrentMonthCalendarIntent()
}
