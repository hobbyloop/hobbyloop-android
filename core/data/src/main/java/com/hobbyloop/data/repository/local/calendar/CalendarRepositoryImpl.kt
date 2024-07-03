package com.hobbyloop.data.repository.local.calendar

import com.hobbyloop.domain.entity.calendar.CalendarYear
import com.hobbyloop.domain.entity.calendar.DateInfo
import com.hobbyloop.domain.repository.calendar.CalendarRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalendarRepositoryImpl @Inject constructor(
    private val datesDataSource: DatesDataSource,
) : CalendarRepository {
    override fun getYears(): CalendarYear {
        return datesDataSource.getYearList()
    }

    override fun getCurrentMonth(): List<DateInfo> {
        return datesDataSource.getDatesOfCurrentMonth()
    }
}
