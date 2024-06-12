package com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.component.shape.Circle
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.CalendarDay
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.CalendarMonth
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.DaySelected
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.DaySelectedStatus
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.dayTypeColor

val DayStatusKey = SemanticsPropertyKey<DaySelectedStatus>("DayStatusKey")
var SemanticsPropertyReceiver.dayStatusProperty by DayStatusKey

@Composable
fun DayCell(
    calendarDay: CalendarDay,
    selectedDay: DaySelected?,
    onDaySelected: (DaySelected) -> Unit,
    month: CalendarMonth,
    modifier: Modifier = Modifier
) {
    val currentDaySelected = DaySelected(
        day = if (calendarDay.day > 0) calendarDay.day else -1,
        month = month.calendarTypeMonth + 1,
        year = month.year,
        classInfoList = calendarDay.reservation
    )

    val isClickable = calendarDay.status != DaySelectedStatus.NonClickable

    DayContainer(
        modifier = modifier.semantics {
            if (isClickable) text =
                AnnotatedString("${month.name} ${calendarDay.day} ${month.year}")
            dayStatusProperty = calendarDay.status
        },
        isSelected = calendarDay.status != DaySelectedStatus.NoSelected,
        onDayClick = {
            onDaySelected(currentDaySelected)
        },
        isClickable = isClickable,
        backgroundColor = Color.Transparent,
        onClickLabel = "Day ${calendarDay.day} Clickable"
    ) {
        DayStatusIndicator(
            calendarDay = calendarDay,
            monthName = month.name,
            year = month.year,
            selectedDay = selectedDay,
        ) {
            if (calendarDay.day != -1) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .clearAndSetSemantics {},
                    text = calendarDay.day.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = calendarDay.status.dayTypeColor()
                    )
                )
            }
        }
    }
}

@Composable
fun DayContainer(
    modifier: Modifier = Modifier,
    cellSize: Dp = 48.dp,
    backgroundColor: Color = Color.Transparent,
    isSelected: Boolean = false,
    isClickable: Boolean = true,
    onClickLabel: String? = null,
    onDayClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val stateDescriptionLabel = if (isSelected) "Selected" else "Not Selected"

    var finalModifier = modifier
        .size(width = cellSize, height = cellSize)
        .semantics {
            stateDescription = stateDescriptionLabel
            contentDescription = onClickLabel ?: ""
        }

    if (isClickable) {
        finalModifier = finalModifier.clickable(
            onClick = {
                onDayClick()
            },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )
    }

    Surface(
        modifier = finalModifier,
        color = backgroundColor
    ) {
        content()
    }
}

@Composable
fun DayStatusIndicator(
    calendarDay: CalendarDay,
    monthName: String,
    year: Int,
    selectedDay: DaySelected?,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (calendarDay.day.toString() == selectedDay?.day.toString() && monthName.dropLast(1) == selectedDay?.month.toString() && selectedDay?.year == year) {
            Circle(
                color = Purple,
                size = 26.dp
            )
        }
        if (calendarDay.reservation != null) {
            Icon(
                painter = painterResource(id = R.drawable.dot_ic),
                contentDescription = "Reservation",
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDayCell() {
    val sampleDay = CalendarDay(15, DaySelectedStatus.Selected, null)

    val sampleSelectedDay = DaySelected(
        day = 15,
        month = 4,
        year = 2023
    )

    val sampleMonth = CalendarMonth("July", 2023, 31)

    DayCell(
        calendarDay = sampleDay,
        selectedDay = sampleSelectedDay,
        onDaySelected = { },
        month = sampleMonth
    )
}
