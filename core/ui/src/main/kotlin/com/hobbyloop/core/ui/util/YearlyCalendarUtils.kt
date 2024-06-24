package com.hobbyloop.core.ui.util

import com.hobbyloop.domain.entity.calendar.DaySelected
import java.util.Calendar

object YearlyCalendarUtils {

    // 현재 월의 인덱스를 반환하는 함수
    fun getCurrentMonthIndex(startYear: Int): Int {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)

        val yearDifference = currentYear - startYear
        val monthOffset = yearDifference * 12

        val monthIndex = currentMonth

        // 총 인덱스는 시작년도에서 몇 년이 지났는지에 따른 월의 수와 현재 월 인덱스를 더한 값
        return monthOffset + monthIndex
    }

    fun getCurrentDate(): DaySelected {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0부터 시작하므로 +1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DaySelected(year, month, day) // reservation은 기본적으로 null
    }
}
