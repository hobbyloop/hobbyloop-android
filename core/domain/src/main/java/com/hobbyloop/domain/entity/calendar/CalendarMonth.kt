package com.hobbyloop.domain.entity.calendar

import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor
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
        year = year,
        startDayOfWeek = getFirstDayOfMonth(
            year = year,
            month = calendarTypeMonth
        ),
        numDays = getNumberOfDaysInMonth(
            year = year,
            month = calendarTypeMonth
        ),
        monthNumber = calendarTypeMonth + 1,
        holidays = holidays
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

            calendarDay.reservation =
                if (selectedReservations.isNotEmpty()) selectedReservations else null
        }
    }
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
    year: Int,
    startDayOfWeek: DayOfWeek,
    numDays: Int,
    monthNumber: Int,
    holidays: Map<Int, Set<String>>
): List<CalendarDay> {
    val days = mutableListOf<CalendarDay>()
    var currentDayOfWeek = startDayOfWeek.ordinal

    // 해당 년도의 공휴일을 가져옵니다.
    val yearHolidays = holidays[year] ?: emptySet()

    for (i in 1..startDayOfWeek.ordinal) {
        days.add(
            CalendarDay(
                day = -1,
                status = DaySelectedStatus.NonClickable
            )
        )
    }

    for (day in 1..numDays) {
        val dateStr = "$monthNumber/$day"
        val status = when {
            yearHolidays.contains(dateStr) -> DaySelectedStatus.Holiday
            currentDayOfWeek == 6 || currentDayOfWeek == 0 -> DaySelectedStatus.Weekend
            else -> DaySelectedStatus.NoSelected
        }
        days.add(
            CalendarDay(
                day,
                status
            )
        )

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

// TODO 간단한 공휴일 데이터 (추후 실제 데이터로 대체 필요), 추후 API를 통해 가져오거나 실제 달력을 비교하여 데이터를 넣던지 해야함
private val holidays = mapOf(
    2024 to setOf("1/1", "2/9", "2/10", "2/11", "3/1", "5/5", "5/15", "5/12", "6/6", "8/15", "9/16", "9/17", "9/18", "10/3", "10/9", "12/25"),
    2025 to setOf("1/1", "1/28", "1/29", "1/30", "3/1", "5/5", "5/15", "6/6", "8/15", "10/3", "10/9", "12/25")
)
