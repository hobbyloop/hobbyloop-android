package com.hobbyloop.data.repository.local.calendar

import com.hobbyloop.domain.entity.calendar.CalendarMonth
import com.hobbyloop.domain.entity.calendar.CalendarYear
import com.hobbyloop.domain.entity.calendar.DateInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatesLocalDataSource @Inject constructor() : DatesDataSource {

    // (Yearly_Calendar 사용)
    override fun getYearList(): CalendarYear {
        return (2024..2024).flatMap { year ->
            createYearData(year)
        }
    }

    // (Monthly_Calendar 사용)
    override fun getDatesOfCurrentMonth(): List<DateInfo> {
        return createCurrentMonth()
    }

    // 주어진 년도에 대한 월의 날짜 정보를 생성 하는 함수(Yearly_Calendar 사용)
    private fun createYearData(year: Int, locale: Locale = Locale.KOREA): List<CalendarMonth> {
        return (Calendar.JANUARY..Calendar.DECEMBER).map { month ->
            CalendarMonth(
                name = monthToName(month, locale),
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

    // 월 이름을 반환하는 함수
    private fun monthToName(month: Int, locale: Locale = Locale.KOREA): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        val monthNameFormat = SimpleDateFormat("MMMM", locale)
        return monthNameFormat.format(calendar.time)
    }
}
