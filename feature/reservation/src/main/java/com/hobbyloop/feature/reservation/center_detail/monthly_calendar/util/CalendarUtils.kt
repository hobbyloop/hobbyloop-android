package com.hobbyloop.feature.reservation.center_detail.monthly_calendar.util

import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.model.DateInfo
import java.util.Calendar

object MonthlyCalendarUtils {

    // 주어진 날짜 목록에서 오늘 날짜에 해당하는 인덱스를 찾아 반환하는 함수
    fun findIndexOfTodayInList(dateInfoList: List<DateInfo>): Int {
        val today = Calendar.getInstance()

        val currentYear = today.get(Calendar.YEAR)
        val currentMonth = today.get(Calendar.MONTH) + 1  // Calendar.MONTH는 0부터 시작하므로 1을 더해줍니다.
        val currentDay = today.get(Calendar.DAY_OF_MONTH)

        // 전체 리스트에서 현재 날짜와 일치하는 첫 번째 인덱스를 찾습니다.
        dateInfoList.forEachIndexed { index, dateInfo ->
            if (dateInfo.year == currentYear && dateInfo.month == currentMonth && dateInfo.day == currentDay) {
                return index
            }
        }

        // 일치하는 날짜가 없는 경우 -1 반환
        return -1
    }
}
