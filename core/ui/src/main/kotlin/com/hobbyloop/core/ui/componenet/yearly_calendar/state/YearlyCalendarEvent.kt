package com.hobbyloop.core.ui.componenet.yearly_calendar.state

import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor

sealed class YearlyCalendarEvent {
    data class ReservationsLoaded(val classInfo: List<Pair<Instructor, List<ClassInfo>>>) : YearlyCalendarEvent()
    data class LoadFailed(val error: String) : YearlyCalendarEvent()
}
