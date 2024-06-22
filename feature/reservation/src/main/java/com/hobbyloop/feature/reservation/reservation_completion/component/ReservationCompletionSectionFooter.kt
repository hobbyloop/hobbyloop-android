package com.hobbyloop.feature.reservation.reservation_completion.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
import theme.HobbyLoopColor.Gray100
import theme.HobbyLoopColor.Gray80
import theme.HobbyLoopTypo

@Composable
fun ReservationCompletionSectionFooter(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "취소를 원하실 경우, ",
                style = HobbyLoopTypo.caption12.copy(lineHeight = 21.sp)
            )
            Text(
                text = "결제하신 업체에 문의 후 취소 가능해요.",
                style = HobbyLoopTypo.head14.copy(lineHeight = 21.sp)

            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "상황에 따라 취소가 불가할 수 있으니 사전에 꼭 알아보세요!",
            style = HobbyLoopTypo.caption12.copy(lineHeight = 21.sp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dot),
                    tint = HobbyLoopColor.Primary,
                    contentDescription = "Reservation",
                )
                Text(
                    text = "고객센터 문의 안내",
                    style = HobbyLoopTypo.head14
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_call),
                    contentDescription = "ReservationCallIcon",
                    tint = Gray100
                )
                Text(
                    text = "예약취소 : 업체문의",
                    style = HobbyLoopTypo.caption12.copy(color = Gray80)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = "ReservationChatIcon",
                    tint = Gray100
                )
                Text(
                    text = "플랫폼 시스템 오류 : 플랫폼 고객센터 문의",
                    style = HobbyLoopTypo.caption12.copy(color = Gray80)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewReservationCompletionSectionFooter() {
    Surface {
        ReservationCompletionSectionFooter(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}
