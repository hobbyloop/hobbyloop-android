package com.hobbyloop.core.ui.componenet.yearly_calendar.state

import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor
import com.hobbyloop.data.repository.local.calendar.model.DaySelected

sealed class YearlyCalendarIntent {
    data class LoadReservations(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : YearlyCalendarIntent()
    data class SelectDay(val daySelected: DaySelected) : YearlyCalendarIntent()
    data object NextMonth : YearlyCalendarIntent()
    data object PreviousMonth : YearlyCalendarIntent()
}
