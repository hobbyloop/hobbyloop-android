package com.hobbyloop.feature.reservation.reservation_completion.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.reservation.ticket.TicketCard
import com.hobbyloop.feature.reservation.component.section.SectionHeader
import com.hobbyloop.ui.R
import theme.HobbyLoopTypo

@Composable
fun ReservationCompletionMethodSection(
    modifier: Modifier,
    centerIconImageUrl: String,
    classTitle: String,
    centerName: String,
    instructorName: String,
    classTime: String,
) {
    Column(
        modifier = modifier
    ){
        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader(
            title = "예약방법",
            iconRes = R.drawable.ic_ticket_color,
            textStyle = HobbyLoopTypo.head16,
            iconDescription = "예약 아이콘",
            iconPadding = PaddingValues(top = 4.dp, bottom = 4.dp, start = 2.75.dp, end = 3.25.dp)
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 16.dp)
        ) {
            TicketCard(
                centerIconImageUrl = centerIconImageUrl,
                classTitle = classTitle,
                centerName = centerName,
                instructorName = instructorName,
                classTime = classTime,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewReservationCompletionMethodSection() {
    Surface {
        ReservationCompletionMethodSection(
            modifier = Modifier.padding(horizontal = 16.dp),
            centerIconImageUrl = "",
            classTitle = "6:1 체형교정 필라테스",
            centerName = "필라피티 스튜디오",
            instructorName = "이민주",
            classTime = "2023. 5. 12 금 09:00 - 09:50",
        )
    }
}
