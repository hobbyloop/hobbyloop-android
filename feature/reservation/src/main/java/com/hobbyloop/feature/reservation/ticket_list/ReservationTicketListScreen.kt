package com.hobbyloop.feature.reservation.ticket_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.model.CenterInfo
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Ticket
import com.hobbyloop.feature.reservation.ticket_list.component.ReservationTicketListHeader
import com.hobbyloop.feature.reservation.ticket_list.component.ticket_card.TicketCard
import com.hobbyloop.feature.reservation.ticket_list.component.top_bar.ReservationTicketListAppBar

@Composable
internal fun ReservationTicketListScreen(
    viewModel: ReservationTicketListViewModel = hiltViewModel(),
    navigateToReservationTicketDetail: (classId: String) -> Unit,
) {
    val ticketList = viewModel.createDummyTicketList()

    ReservationTicketListScreen(
        ticketList = ticketList,
        navigateToReservationTicketDetail = navigateToReservationTicketDetail
    )
}

@Composable
internal fun ReservationTicketListScreen(
    ticketList: List<Ticket>,
    navigateToReservationTicketDetail: (classId: String) -> Unit,
) {
    Scaffold(
        topBar = {
            ReservationTicketListAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .padding(17.dp),
                onSearchClick = { },
                onNotificationClick = { },
            )
        },
        containerColor = Color.White,
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                ReservationTicketListHeader(totalTicketCount = ticketList.size)
            }

            items(
                count = ticketList.size,
                key = { index -> ticketList[index].ticketInfo.first.centerId }
            ) { index ->
                ticketList[index].let { ticket ->
                    TicketCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                        centerIconImageUrl = ticket.ticketInfo.first.centerProfileImageUrl,
                        centerName = ticket.ticketInfo.first.centerName,
                        isRefundable = ticket.ticketInfo.first.isRefundable,
                        classInfoList = ticket.ticketInfo.second,
                        navigateToReservationTicketDetail = { ticketId ->
                            navigateToReservationTicketDetail(ticketId)
                        }
                    )

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Gray20))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewReservationTicketListScreen() {
    val centerInfo1 = CenterInfo(
        centerId = 1.toString(),
        centerProfileImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAyMDNfMTc0%2FMDAxNjc1NDA2NjgzNzI1.9DYXly_t1f9R0B65qD6OUXEgV3KHE1l_1SF_SiTH83sg.mcvrKhIgHJwr7l6_DkDv_6aVfLMSbK_AVERzDvIPV8gg.JPEG.brandingpicks%2F%25B1%25E2%25BE%25F7_%25C8%25B8%25BB%25E7_%25B7%25CE%25B0%25ED%25B5%25F0%25C0%25DA%25C0%25CE_%25C1%25A6%25C0%25DB8.jpg&type=sc960_832",
        centerName = "발란스 스튜디오",
        isRefundable = true
    )

    val centerInfo2 = CenterInfo(
        centerId = 2.toString(),
        centerProfileImageUrl = "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fi.pinimg.com%2F736x%2Fab%2Fa3%2Fb7%2Faba3b7628d618fb91eca54bb7f4fe709.jpg&type=sc960_832",
        centerName = "나마스카르 요가학원",
        isRefundable = false
    )

    val centerInfo3 = CenterInfo(
        centerId = 3.toString(),
        centerProfileImageUrl = "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fimg.freepik.com%2Fpremium-vector%2Fn-letter-business-company-logo-design_9955-450.jpg&type=sc960_832",
        centerName = "조 GYM",
        isRefundable = true
    )

    val centerInfo4 = CenterInfo(
        centerId = 4.toString(),
        centerProfileImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAzMTVfMjM2%2FMDAxNjQ3MzA4NzM3NjIy.kp50xWQQrUInNnJRC6LZ1Ve7kAIFxn5q2XuZszSIHpog.c5ACYtOgsidukbMVuFXZiz8xZipxQoMnjd2i0nrtqv0g.PNG.sangsang_is%2F%25B7%25CE%25B0%25ED_%25286%2529.png&type=sc960_832",
        centerName = "김권투의 권투교실",
        isRefundable = false
    )

    val classInfoList1 = listOf(
        ClassInfo(1, "2024-05-11 08:00 - 09:00", "아침 요가", "초급", 10, 12),
        ClassInfo(2, "2024-05-11 10:00 - 11:00", "고급 요가", "고급", 5, 5)
    )

    val classInfoList2 = listOf(
        ClassInfo(3, "2024-05-12 08:00 - 09:00", "아침 필라테스", "초급", 8, 10),
        ClassInfo(4, "2024-05-12 10:00 - 11:00", "고급 필라테스", "고급", 7, 7)
    )

    val classInfoList3 = listOf(
        ClassInfo(5, "2024-05-13 08:00 - 09:00", "아침 스피닝", "중급", 6, 8),
        ClassInfo(6, "2024-05-13 10:00 - 11:00", "고급 스피닝", "고급", 4, 6)
    )

    val classInfoList4 = listOf(
        ClassInfo(7, "2024-05-14 08:00 - 09:00", "아침 필라테스", "초급", 8, 10),
        ClassInfo(8, "2024-05-14 10:00 - 11:00", "고급 필라테스", "고급", 7, 7)
    )

    val ticket1 = Ticket(
        ticketInfo = Pair(centerInfo1, classInfoList1)
    )

    val ticket2 = Ticket(
        ticketInfo = Pair(centerInfo2, classInfoList2)
    )

    val ticket3 = Ticket(
        ticketInfo = Pair(centerInfo3, classInfoList3)
    )

    val ticket4 = Ticket(
        ticketInfo = Pair(centerInfo4, classInfoList4)
    )

    val ticketList = listOf(ticket1, ticket2, ticket3, ticket4)

    ReservationTicketListScreen(
        ticketList = ticketList,
        navigateToReservationTicketDetail = {}
    )
}
