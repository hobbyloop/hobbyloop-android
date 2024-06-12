package com.hobbyloop.feature.reservation.reservation_completion.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.feature.reservation.Gray80
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.R

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
                style = TextStyle(
                    fontSize = 13.sp,
                )
            )
            Text(
                text = "결제하신 업체에 문의 후 취소 가능해요.",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700)
                )
            )
        }
        Text(
            text = "상황에 따라 취소가 불가할 수 있으니 사전에 꼭 알아보세요!",
            style = TextStyle(
                fontSize = 13.sp,
            )
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
                    painter = painterResource(id = R.drawable.dot_ic),
                    tint = Purple,
                    contentDescription = "Reservation",
                )
                Text(
                    text = "고객센터 문의 안내",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700)
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.call_ic),
                    tint = Purple,
                    contentDescription = "Reservation",
                )
                Text(
                    text = "예약취소 : 업체문의",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Gray80
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_ic),
                    tint = Purple,
                    contentDescription = "Reservation",
                )
                Text(
                    text = "플랫폼 시스템 오류 : 플랫폼 고객센터 문의",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Gray80
                    )
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
