package com.hobbyloop.feature.reservation.center_detail.yearly_calendar.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.CalendarDay
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.CalendarMonth
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.CalendarWeek
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelected
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelectedStatus

@Composable
fun Week(
    modifier: Modifier = Modifier,
    month: CalendarMonth,
    week: CalendarWeek,
    selectedDay: DaySelected?,
    onDayClicked: (DaySelected) -> Unit
) {
    Row(modifier = modifier) {
        for (day in week) {
            DayCell(
                calendarDay = day,
                month = month,
                selectedDay = selectedDay,
                onDaySelected = { daySelected ->
                    onDayClicked(daySelected)
                }

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeek() {
    val sampleMonth = CalendarMonth(
        name = "October",
        year = 2023,
        calendarTypeMonth = 2
    )

    val sampleWeek = List(7) { day ->
        CalendarDay(
            day = (day + 1),
            status = DaySelectedStatus.NoSelected
        )
    }

    val sampleSelectedDay = DaySelected(
        day = 1,
        month = 4,
        year = 2024
    )

    Week(
        month = sampleMonth,
        week = sampleWeek,
        selectedDay = sampleSelectedDay,
        onDayClicked = { }
    )
}
