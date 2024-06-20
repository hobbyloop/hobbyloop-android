package com.hobbyloop.core.ui.componenet.yearly_calendar.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.data.repository.local.calendar.model.CalendarDay
import com.hobbyloop.data.repository.local.calendar.model.CalendarMonth
import com.hobbyloop.data.repository.local.calendar.model.DaySelected
import com.hobbyloop.data.repository.local.calendar.model.DaySelectedStatus
import theme.HobbyLoopColor

@Composable
fun DaysOfMonth(
    month: CalendarMonth,
    selectedDay: DaySelected?,
    onDayClicked: (DaySelected) -> Unit,
) {
    Column {
        val contentModifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)

        DaysOfWeek(modifier = contentModifier)

        Spacer(modifier = Modifier.height(6.dp))

        Divider(
            color = HobbyLoopColor.Gray20,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        )

        // 각 주를 나타내는 Week 컴포저블을 반복하여 나열함
        month.weeks.value.forEachIndexed { index, week ->
            Week(
                modifier = contentModifier,
                week = week,
                month = month,
                selectedDay = selectedDay,
                onDayClicked = { daySelected ->
                    onDayClicked(daySelected)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDaysOfMonth() {
    val sampleMonth = CalendarMonth(
        name = "October",
        year = 2023,
        calendarTypeMonth = 2,
        calendarDays = List(31) { day ->
            CalendarDay(
                day = (day + 1),
                status = DaySelectedStatus.NoSelected
            )
        }
    )

    val sampleSelectedDay = DaySelected(
        day = 15,
        month = 4,
        year = 2023
    )

    DaysOfMonth(
        month = sampleMonth,
        selectedDay = sampleSelectedDay,
        onDayClicked = { }
    )
}
