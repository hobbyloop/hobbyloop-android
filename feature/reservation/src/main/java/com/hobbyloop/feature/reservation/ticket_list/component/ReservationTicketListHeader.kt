package com.hobbyloop.feature.reservation.ticket_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.HobbyLoopColor
import theme.HobbyLoopTypo
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
        val ticketCountText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Pink, fontSize = 23.sp)) {
                append(totalTicketCount.toString())
            }
            append("개의 ")
            append(" 이용권이 있어요.")
        }
        Text(
            text = ticketCountText,
            style = HobbyLoopTypo.head22,
        )

        Text(
            text = "예약하고 싶은 수업의 이용권을 선택해 주세요.",
            style = HobbyLoopTypo.body16.copy(color = HobbyLoopColor.Gray60)
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
