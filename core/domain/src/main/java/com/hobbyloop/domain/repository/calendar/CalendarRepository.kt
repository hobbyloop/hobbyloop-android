package com.hobbyloop.domain.repository.calendar

import com.hobbyloop.domain.entity.calendar.CalendarYear
import com.hobbyloop.domain.entity.calendar.DateInfo

interface CalendarRepository {

    /**
     * Yearly_Calendar 사용
     */
    fun getYears() : CalendarYear

    /**
     * Monthly_Calendar 사용
     */
    fun getCurrentMonth() : List<DateInfo>
}
