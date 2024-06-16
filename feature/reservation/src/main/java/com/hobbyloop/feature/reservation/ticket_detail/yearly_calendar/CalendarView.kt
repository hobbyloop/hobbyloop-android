package com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.component.button.NavigationIconButton
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.CurrentMonthlyReservationCalendar
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.DaySelected

@Composable
fun CalendarView(
    classData: List<Pair<Instructor, List<ClassInfo>>>, // 수업 정보 및 강사 정보가 포함된 리스트
    onResetInstructorDetailsVisible: () -> Unit = {},
    content: @Composable (DaySelected) -> Unit
) {
    var calendarTypeState by remember {
        mutableStateOf(CalendarType.MONTH)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
    ) {
        NavigationIconButton(
            isEnabled = true,
            iconId = R.drawable.calendar_ic,
            description = "Change Calendar",
            onClick = {
                calendarTypeState = when (calendarTypeState) {
                    CalendarType.YEAR -> CalendarType.MONTH
                    CalendarType.MONTH -> CalendarType.YEAR
                }
            },
            enabledColor = when (calendarTypeState) {
                CalendarType.YEAR -> Purple
                CalendarType.MONTH -> Color.Black
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
                .zIndex(1f)
        )

        when (calendarTypeState) {
            CalendarType.YEAR -> {
                YearlyReservationCalendar(
                    classData = classData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                        .zIndex(0f)
                ) { daySelected ->
                    LaunchedEffect(daySelected) {
                        onResetInstructorDetailsVisible()
                    }
                    content(daySelected)
                }
            }

            CalendarType.MONTH -> {
                CurrentMonthlyReservationCalendar(
                    classData = classData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                        .zIndex(0f)
                ) { daySelected ->
                    LaunchedEffect(daySelected) {
                        onResetInstructorDetailsVisible()
                    }
                    content(daySelected)
                }
            }
        }
    }
}

enum class CalendarType {
    YEAR, MONTH
}
