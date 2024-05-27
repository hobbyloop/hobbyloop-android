package com.hobbyloop.feature.reservation.center_detail.yearly_calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.center_detail.component.button.NavigationIconButton

@Composable
fun MonthHeader(
    modifier: Modifier = Modifier,
    month: String,
    year: String,
    selectedMonthIndex: Int,
    endMonthIndex: Int,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val canNavigateToPrevious = selectedMonthIndex > 0
        val canNavigateToNext = selectedMonthIndex < endMonthIndex - 1

        NavigationIconButton(
            isEnabled = canNavigateToPrevious,
            iconId = R.drawable.arrow_left_ic,
            description = "Previous month",
            onClick = onPreviousMonthClick,
            enabledColor = LocalContentColor.current,
            disabledColor = Color.Gray
        )

        Text(
            text = "${year}년 ${month}월",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        NavigationIconButton(
            isEnabled = canNavigateToNext,
            iconId = R.drawable.arrow_right_ic,
            description = "Next month",
            onClick = onNextMonthClick,
            enabledColor = LocalContentColor.current,
            disabledColor = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMonthHeader() {
    MonthHeader(
        month = "5",
        year = "2024",
        selectedMonthIndex = 0,
        endMonthIndex = 11,
        onPreviousMonthClick = { },
        onNextMonthClick = { }
    )
}
