package com.hobbyloop.feature.reservation.center_detail.yearly_calendar.util

import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.CalendarDay
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DayOfWeek
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelected
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelectedStatus
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object YearlyCalendarUtils {

    // 간단한 공휴일 데이터 (추후 실제 데이터로 대체 필요) + 추후 다른 년도를 추가 확장한다면 년도+월일의 값을 넣어서 확장 해야함
    private val holidays2024 = setOf("1/1", "3/1", "5/5", "6/6", "8/15", "10/3", "10/9", "12/25")

    // 월 이름을 반환하는 함수
    fun monthToName(month: Int, locale: Locale = Locale.KOREA): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        val monthNameFormat = SimpleDateFormat("MMMM", locale)
        return monthNameFormat.format(calendar.time)
    }

    // 첫번째 요일을 얻는 함수
    fun getFirstDayOfMonth(year: Int, month: Int): DayOfWeek {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)
        return DayOfWeek.from(calendar.get(Calendar.DAY_OF_WEEK))
    }

    // 해당 월의 일수를 계산하는 함수
    fun getNumberOfDaysInMonth(year: Int, month: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    // 지정된 월의 모든 날짜에 대한 요일을 리스트로 그룹화 하는 함수
    fun createDaysList(
        startDayOfWeek: DayOfWeek,
        numDays: Int,
        monthNumber: Int,
        holidays: Set<String> = holidays2024
    ): List<CalendarDay> {
        val days = mutableListOf<CalendarDay>()
        var currentDayOfWeek = startDayOfWeek.ordinal

        for (i in 1..startDayOfWeek.ordinal) {
            days.add(CalendarDay(day = -1, status = DaySelectedStatus.NonClickable))
        }

        for (day in 1..numDays) {
            val dateStr = "$monthNumber/$day"
            val status = when {
                holidays.contains(dateStr) -> DaySelectedStatus.Holiday
                currentDayOfWeek == 6 || currentDayOfWeek == 0 -> DaySelectedStatus.Weekend
                else -> DaySelectedStatus.NoSelected
            }
            days.add(CalendarDay(day, status))

            currentDayOfWeek = (currentDayOfWeek + 1) % 7
        }

        return days.toList()
    }

    // 일주일을 완성하기 위해 필요한 만큼 빈 'CalendarDay' 인스턴스를 추가하여 리스트를 완성시키는 함수
    fun completeWeek(list: List<CalendarDay>): List<CalendarDay> {
        var gapsToFill = 7 - list.size

        return if (gapsToFill != 0) {
            val mutableList = list.toMutableList()
            while (gapsToFill > 0) {
                mutableList.add(
                    CalendarDay(
                        -1, // 날짜 값 없음
                        DaySelectedStatus.NonClickable // 선택 불가능 상태
                    )
                )
                gapsToFill--
            }
            mutableList
        } else {
            list
        }
    }

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

    // 정규 표현식을 사용하여 HH:mm - HH:mm" 형식의 시간 부분을 추출하는 함수
    fun extractTime(dateTime: String): String {
        val regex = "\\d{2}:\\d{2} - \\d{2}:\\d{2}".toRegex() // "HH:mm - HH:mm" 형식 매칭
        return regex.find(dateTime)?.value ?: "시간 정보 없음" // 매칭되는 첫 번째 결과 반환, 없으면 기본 텍스트 반환
    }

    fun getCurrentDate(): DaySelected {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0부터 시작하므로 +1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DaySelected(year, month, day) // reservation은 기본적으로 null
    }
}
