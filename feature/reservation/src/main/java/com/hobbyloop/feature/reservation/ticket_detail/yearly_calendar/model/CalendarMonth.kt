package com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model

import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.util.YearlyCalendarUtils.completeWeek
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.util.YearlyCalendarUtils.createDaysList
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.util.YearlyCalendarUtils.getFirstDayOfMonth
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.util.YearlyCalendarUtils.getNumberOfDaysInMonth
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * 한 달의 기본 정보를 담는 데이터 클래스
 */
data class CalendarMonth(
    val name: String,  // 달의 이름 (예: "January")
    val year: Int,  // 연도 (예: "2024")
    val calendarTypeMonth: Int, // Calendar API의 Month 값(1월 기준 값: 0)
    val calendarDays: List<CalendarDay> = createDaysList( // 월의 모든 날짜(일)에 대한 데이터
        startDayOfWeek = getFirstDayOfMonth(
            year = year,
            month = calendarTypeMonth
        ),
        numDays = getNumberOfDaysInMonth(
            year = year,
            month = calendarTypeMonth
        ),
        monthNumber = calendarTypeMonth + 1
    )
) {
    val month = calendarTypeMonth + 1 // 달의 수

    /**
     * 'weeks'는 일주일 단위로 날짜 데이터를 그룹화하고, 각 주를 완성하기 위한 로직을 적용한 결과를
     * 'lazy' 키워드를 사용하여 이 변수의 값이 처음 접근될 때 한 번만 계산되고, 그 후에는 저장된 값을 반환하여 사용함
     */
    val weeks = lazy { calendarDays.chunked(7).map { completeWeek(it) } }

    /**
     * 주어진 수업 예약 목록을 캘린더의 연도, 월, 날짜를 비교하여 일치하는 각 날짜에 reservation 값을 할당하는 함수
     *
     * @param reservations 캘린더에 할당할 수업 예약 목록
     */
    fun assignReservations(classInfo: List<Pair<Instructor, List<ClassInfo>>>) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        calendarDays.forEach { calendarDay ->
            val calendar = Calendar.getInstance()
            calendar.set(year, calendarTypeMonth, calendarDay.day)  // 월은 0에서 시작하므로 -1
            val dayDate = calendar.time

            val selectedReservations = mutableListOf<Pair<Instructor, List<ClassInfo>>>()

            classInfo.forEach { (instructor, classes) ->
                val matchingClasses = classes.filter { classInfo ->
                    // "2024-05-11 08:00 - 09:00" 같은 dateTime에서 날짜 부분만 파싱
                    val classDate = dateFormat.parse(classInfo.dateTime.substring(0, 10))
                    // dayDate와 classDate의 날짜만 비교
                    dateFormat.format(classDate) == dateFormat.format(dayDate)
                }
                if (matchingClasses.isNotEmpty()) {
                    selectedReservations.add(Pair(instructor, matchingClasses))
                }
            }

            calendarDay.reservation = if (selectedReservations.isNotEmpty()) selectedReservations else null
        }
    }
}
