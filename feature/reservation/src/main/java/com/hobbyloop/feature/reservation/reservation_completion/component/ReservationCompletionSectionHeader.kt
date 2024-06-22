package com.hobbyloop.feature.reservation.reservation_completion.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.box.GradientBox
import com.hobbyloop.core.ui.util.TextUtil.extractFirstName
import theme.HobbyLoopColor
import theme.HobbyLoopColor.Gray80
import theme.HobbyLoopTypo

@Composable
fun ReservationCompletionSectionHeader(
    name: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            GradientBox(
                modifier = Modifier
                    .width(36.dp)
                    .height(28.dp),
                borderWidth = 2.dp,
                gradientColors = listOf(
                    HobbyLoopColor.Primary,
                    HobbyLoopColor.Primary
                ),
                borderShape = RoundedCornerShape(
                    topStart = 8.dp,
                    bottomStart = 8.dp
                ),
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = Icons.Filled.Check,
                    contentDescription = "체크 아이콘",
                    tint = Color.Black
                )
            }

            Text(
                text = "예약이 완료되었어요!",
                style = HobbyLoopTypo.head22
            )
        }

        Column(
            modifier = Modifier.padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${name.extractFirstName()}님의 만족스러운 방문이 되시길 바랄게요.",
                style = HobbyLoopTypo.head14.copy(Gray80)
            )

            Text(
                text = "예약 하신 시간에 맞춰 방문 부탁드려요!",
                style = HobbyLoopTypo.head14.copy(Gray80)
            )
        }
    }
}

@Preview
@Composable
fun PreviewReservationCompletionSectionHeader() {
    Surface {
        ReservationCompletionSectionHeader(
            name = "김지원"
        )
    }
}
