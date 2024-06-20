package com.hobbyloop.feature.reservation.reservation_completion.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.ui.R
import com.hobbyloop.core.ui.componenet.reservation.section.SectionHeader
import com.hobbyloop.core.ui.util.TextUtil.formatAsPhoneNumber
import theme.HobbyLoopColor

@Composable
fun ReservationCompletionUserInfoSection(
    modifier: Modifier,
    name: String,
    phoneNumber: String,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionHeader(
                title = "예약자 정보",
                iconRes = R.drawable.ic_user_color,
                isIconTintEnabled = true,
                iconTint = HobbyLoopColor.Primary,
                iconDescription = "공지사항 아이콘",
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "정보는 마이페이지에서 수정 가능해요.",
                style = TextStyle(
                    color = HobbyLoopColor.Gray60,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "예약자",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = HobbyLoopColor.Gray60
                    ),
                    modifier = Modifier.weight(0.3f)
                )
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "전화번호",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = HobbyLoopColor.Gray60
                    ),
                    modifier = Modifier.weight(0.3f)
                )
                Text(
                    text = phoneNumber.formatAsPhoneNumber(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun PreviewReservationCompletionUserInfoSection() {
    Surface {
        ReservationCompletionUserInfoSection(
            modifier = Modifier.padding(horizontal = 16.dp),
            name = "김지원",
            phoneNumber = "01012341234"
        )
    }
}
