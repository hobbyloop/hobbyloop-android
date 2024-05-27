package com.hobbyloop.feature.reservation.center_detail.yearly_calendar.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hobbyloop.feature.reservation.Gray60
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DayOfWeek

@Composable
fun DaysOfWeek(modifier: Modifier = Modifier) {
    Row(modifier = modifier.clearAndSetSemantics { }) {
        for (day in DayOfWeek.entries) {
            WeekdayHeader(name = day.dayAbbrevs)
        }
    }
}

@Composable
fun WeekdayHeader(name: String) {
    DayContainer {
        Text(
            modifier = Modifier.wrapContentSize(Alignment.Center),
            text = name,
            color = Gray60,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDaysOfWeek() {
    DaysOfWeek()
}

@Preview(showBackground = true)
@Composable
fun PreviewWeekdayHeader() {
    WeekdayHeader(name = "일")
}

