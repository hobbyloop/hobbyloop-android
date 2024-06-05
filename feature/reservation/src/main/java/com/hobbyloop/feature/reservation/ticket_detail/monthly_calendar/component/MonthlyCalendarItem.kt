package com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.model.DateInfo

@Composable
fun MonthlyCalendarItem(
    dateInfo: DateInfo,
    backgroundColor: Color,
    textColor: Color
) {
    if (dateInfo.dayOfWeek == "") {
        Spacer(
            modifier = Modifier
                .width(50.dp)
                .height(66.dp)
        )
    } else {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(50.dp)
                .height(66.dp)
                .background(
                    backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dateInfo.dayOfWeek.take(1),
                    fontSize = 14.sp,
                    color = textColor,
                )
                Text(
                    text = dateInfo.day.toString(),
                    fontSize = 14.sp,
                    color = textColor,
                )
            }
        }
    }
}
