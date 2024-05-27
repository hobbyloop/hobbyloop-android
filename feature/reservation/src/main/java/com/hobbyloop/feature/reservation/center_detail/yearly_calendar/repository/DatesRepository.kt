package com.hobbyloop.feature.reservation.center_detail.yearly_calendar.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatesRepository @Inject constructor(
    datesLocalDataSource: DatesLocalDataSource,
) {
    val calendarYear = datesLocalDataSource.yearList
    val datesOfCurrentMonth = datesLocalDataSource.datesOfCurrentMonth
}
