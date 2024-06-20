package com.hobbyloop.feature.reservation.ticket_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import theme.HobbyLoopColor
import theme.Pink

@Composable
fun ReservationTicketListHeader(
    totalTicketCount: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 24.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = totalTicketCount.toString(),
                fontWeight = FontWeight(700),
                color = Pink,
                fontSize = 22.sp
            )
            Text(
                text = "개의 이용권이 있어요.",
                fontWeight = FontWeight(700),
                color = Color.Black,
                fontSize = 22.sp
            )
        }
        Text(
            text = "예약하고 싶은 수업의 이용권을 선택해 주세요.",
            fontWeight = FontWeight(500),
            color = HobbyLoopColor.Gray60,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun PreviewReservationTicketListHeader() {
    Surface {
        ReservationTicketListHeader(totalTicketCount = 3)
    }
}
