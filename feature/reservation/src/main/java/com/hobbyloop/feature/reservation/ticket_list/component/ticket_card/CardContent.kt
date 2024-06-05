package com.hobbyloop.feature.reservation.ticket_list.component.ticket_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.feature.reservation.Gray60
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.component.box.GradientBox
import com.hobbyloop.feature.reservation.component.button.RoundRippleIcon

@Composable
fun CardContent(
    classId: String,
    className: String,
    classPeriod: String,
    navigateToReservationTicketDetail: (classId: String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GradientBox(
            width = 70.dp,
            height = 38.dp,
            borderWidth = 2.dp,
            gradientColors = listOf(
                Color(0xFF88B967),
                Color(0xFFC2DA76),
                Color(0xFFEEDFCC)
            ),
            borderShape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 2.dp,
                bottomEnd = 2.dp,
                bottomStart = 12.dp
            ),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = className,
                fontWeight = FontWeight(700),
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = classPeriod,
                fontWeight = FontWeight(500),
                fontSize = 12.sp,
                color = Gray60
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        RoundRippleIcon(
            iconSize = 26.dp,
            iconResId = R.drawable.arrow_right_ic,
            contentDescription = "수업 예약 상세보기 이동",
            onClick = { navigateToReservationTicketDetail(classId) }
        )
    }
}

@Preview
@Composable
fun PreviewCardContent() {
    Surface {
        CardContent(
            classId = 1.toString(),
            className = "6:1 그룹레슨 6개월",
            classPeriod = "2023.04.20 - 2023.06.20",
            navigateToReservationTicketDetail = { }
        )
    }
}
