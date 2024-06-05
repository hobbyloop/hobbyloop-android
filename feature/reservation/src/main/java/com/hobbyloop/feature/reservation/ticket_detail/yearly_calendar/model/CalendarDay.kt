package com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model

import androidx.compose.ui.graphics.Color
import com.hobbyloop.feature.reservation.Gray40
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor

/**
 * 달의 시작 요일을 담기 위한 데이터 클래스
 */
enum class DayOfWeek(val dayAbbrevs: String) {
    Sunday(dayAbbrevs = "일"),
    Monday(dayAbbrevs = "월"),
    Tuesday(dayAbbrevs = "화"),
    Wednesday(dayAbbrevs = "수"),
    Thursday(dayAbbrevs = "목"),
    Friday(dayAbbrevs = "금"),
    Saturday(dayAbbrevs = "토");

    companion object {
        // Calendar의 DAY_OF_WEEK 인덱스를 enum으로 매핑하는 함수
        fun from(calendarDayOfWeek: Int): DayOfWeek {
            return entries[(calendarDayOfWeek - 1) % entries.size]
        }
    }
}

enum class DaySelectedStatus {
    NoSelected, Selected, NonClickable, Weekend, Holiday
}

data class CalendarDay(
    val day: Int,
    var status: DaySelectedStatus,
    var reservation: List<Pair<Instructor, List<ClassInfo>>>? = null
)

fun DaySelectedStatus.dayTypeColor(): Color = when (this) {
    DaySelectedStatus.Holiday,
    DaySelectedStatus.Weekend -> Gray40
    DaySelectedStatus.Selected -> Color.White
    else -> Color.Black
}
