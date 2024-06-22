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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.util.TextUtil.formatAsPhoneNumber
import com.hobbyloop.feature.reservation.component.section.SectionHeader
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

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
                textStyle = HobbyLoopTypo.head16
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "정보는 마이페이지에서 수정 가능해요.",
                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60)
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
                    style = HobbyLoopTypo.body16.copy(color = HobbyLoopColor.Gray60),
                    modifier = Modifier.weight(0.3f)
                )
                Text(
                    text = name,
                    style = HobbyLoopTypo.body16,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "전화번호",
                    style = HobbyLoopTypo.body16.copy(color = HobbyLoopColor.Gray60),
                    modifier = Modifier.weight(0.3f)
                )
                Text(
                    text = phoneNumber.formatAsPhoneNumber(),
                    style = HobbyLoopTypo.body16,
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
