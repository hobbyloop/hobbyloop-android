package com.hobbyloop.feature.reservation.ticket_list.component.ticket_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.util.DateUtil.formatDateTimeRange

@Composable
fun TicketCard(
    centerImageUrl: String,
    classInfoList: List<ClassInfo>,
    centerName: String,
    isRefundable: Boolean,
    modifier: Modifier = Modifier,
    navigateToReservationTicketDetail: (classId: String) -> Unit,
) {
    Column {
        TicketCardHeader(
            centerImageUrl = centerImageUrl,
            centerName = centerName,
            isRefundable = isRefundable,
            modifier = modifier
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Gray20)
        )

        Column {
            classInfoList.forEach { classInfo ->
                CardContent(
                    classId = classInfo.classId.toString(),
                    className = classInfo.title,
                    classPeriod = formatDateTimeRange(classInfo.dateTime),
                    navigateToReservationTicketDetail = navigateToReservationTicketDetail
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTicketCardIsRefundable() {
    Surface {
        TicketCard(
            centerImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA0MTZfMTA0%2FMDAxNTU1NDE1NTAzNTgx.n4hiEiGSF91TMegRtR5o3SA1RZbE6S6SnrnTGNNunlMg.PJoW33HktJZos6K3ENRRpZs3cNdYgSYv_3ph6RzIDx8g.JPEG.bemine9_9%2F0405_2_3.jpg&type=sc960_832",
            classInfoList = listOf(
                ClassInfo(1, "2024-05-11 08:00 - 09:00", "아침 요가", "초급", 10, 12),
                ClassInfo(2, "2024-05-11 10:00 - 11:00", "고급 요가", "고급", 5, 5)
            ),
            centerName = "발란스 스튜디오",
            isRefundable = true,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            navigateToReservationTicketDetail = { },
        )
    }
}

@Preview
@Composable
fun PreviewTicketCardIsNotRefundable() {
    Surface {
        TicketCard(
            centerImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA0MTZfMTA0%2FMDAxNTU1NDE1NTAzNTgx.n4hiEiGSF91TMegRtR5o3SA1RZbE6S6SnrnTGNNunlMg.PJoW33HktJZos6K3ENRRpZs3cNdYgSYv_3ph6RzIDx8g.JPEG.bemine9_9%2F0405_2_3.jpg&type=sc960_832",
            classInfoList = listOf(
                ClassInfo(1, "2024-05-11 08:00 - 09:00", "아침 요가", "초급", 10, 12),
                ClassInfo(2, "2024-05-11 10:00 - 11:00", "고급 요가", "고급", 5, 5)
            ),
            centerName = "발란스 스튜디오",
            isRefundable = false,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            navigateToReservationTicketDetail = { },
        )
    }
}
