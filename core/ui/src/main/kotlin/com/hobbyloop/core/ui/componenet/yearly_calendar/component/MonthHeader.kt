package com.hobbyloop.core.ui.componenet.yearly_calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hobbyloop.core.ui.componenet.button.NavigationIconButton
import com.hobbyloop.ui.R
import theme.HobbyLoopColor.Gray100
import theme.HobbyLoopColor.Gray40
import theme.HobbyLoopTypo

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
            iconId = R.drawable.ic_back,
            description = "Previous month",
            onClick = onPreviousMonthClick,
            enabledColor = Gray100,
            disabledColor = Gray40
        )

        Text(
            text = "${year}년 ${month}월",
            style = HobbyLoopTypo.head16,
        )

        NavigationIconButton(
            isEnabled = canNavigateToNext,
            iconId = R.drawable.ic_right,
            description = "Next month",
            onClick = onNextMonthClick,
            enabledColor = Gray100,
            disabledColor = Gray40
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
