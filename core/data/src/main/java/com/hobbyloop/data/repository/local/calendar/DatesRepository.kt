package com.hobbyloop.data.repository.local.calendar

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatesRepository @Inject constructor(
    datesLocalDataSource: DatesLocalDataSource,
) {
    val calendarYear = datesLocalDataSource.yearList
    val datesOfCurrentMonth = datesLocalDataSource.datesOfCurrentMonth
}
