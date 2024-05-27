package com.hobbyloop.feature.reservation.center_detail.yearly_calendar.repository

import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.model.DateInfo
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.CalendarMonth
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.CalendarYear
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.util.YearlyCalendarUtils
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatesLocalDataSource @Inject constructor() {

    val yearList: CalendarYear = (2024..2024).flatMap { year ->
        createYearData(year)
    } // (Yearly_Calendar 사용)
    val datesOfCurrentMonth: List<DateInfo> = createCurrentMonth() // (Monthly_Calendar 사용)

    // 주어진 년도에 대한 월의 날짜 정보를 생성 하는 함수(Yearly_Calendar 사용)
    private fun createYearData(year: Int, locale: Locale = Locale.KOREA): List<CalendarMonth> {
        return (Calendar.JANUARY..Calendar.DECEMBER).map { month ->
            CalendarMonth(
                name = YearlyCalendarUtils.monthToName(month, locale),
                year = year,
                calendarTypeMonth = month
            )
        }
    }

    // 현재 월의 모든 날짜 정보를 생성 하는 함수(Monthly_Calendar 사용)
    private fun createCurrentMonth(): List<DateInfo> {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val dateInfoList = mutableListOf<DateInfo>()

        for (day in 1..daysInMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, day)
            val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())

            val dateInfo = DateInfo(
                year = currentYear,
                month = currentMonth + 1,
                day = day,
                dayOfWeek = dayOfWeek ?: "Unknown"
            )
            dateInfoList.add(dateInfo)
        }

        return dateInfoList
    }
}
