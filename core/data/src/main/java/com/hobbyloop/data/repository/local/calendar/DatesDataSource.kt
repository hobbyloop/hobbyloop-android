package com.hobbyloop.data.repository.local.calendar

import com.hobbyloop.domain.entity.calendar.CalendarYear
import com.hobbyloop.domain.entity.calendar.DateInfo

interface DatesDataSource {
    fun getYearList(): CalendarYear
    fun getDatesOfCurrentMonth(): List<DateInfo>
}
