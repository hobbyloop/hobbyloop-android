package com.hobbyloop.feature.reservation.ticket_list.component.ticket_card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hobbyloop.ui.R
import com.hobbyloop.core.ui.componenet.button.SurfaceButton
import theme.HobbyLoopColor

@Composable
fun TicketCardHeader(
    centerIconImageUrl: String,
    centerName: String,
    isRefundable: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = centerIconImageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "업체 이미지",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = HobbyLoopColor.Gray20,
                    shape = CircleShape
                ),
            placeholder = painterResource(id = R.drawable.ic_close),
            error = painterResource(id = R.drawable.ic_close)
        )

        Text(
            text = centerName,
            fontWeight = FontWeight(700),
            fontSize = 16.sp,
            color = HobbyLoopColor.Gray60
        )

        if (isRefundable) {
            SurfaceButton(
                text = "중도환불 가능업체",
                horizontalPadding = 12.dp,
                verticalPadding = 8.dp,
                fontSize = 14.sp,
                selectable = false,
                unselectedTextColor = Color.Black,
                unselectedBorderColor = HobbyLoopColor.Gray40,
                shape =  RoundedCornerShape(999.dp),
                borderWidth = 1.dp,
                isSelected = false,
            )
        }
    }
}

@Preview
@Composable
fun PreviewTicketCardHeaderNonRefundable() {
    Surface {
        TicketCardHeader(
            centerIconImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA0MTZfMTA0%2FMDAxNTU1NDE1NTAzNTgx.n4hiEiGSF91TMegRtR5o3SA1RZbE6S6SnrnTGNNunlMg.PJoW33HktJZos6K3ENRRpZs3cNdYgSYv_3ph6RzIDx8g.JPEG.bemine9_9%2F0405_2_3.jpg&type=sc960_832",
            centerName = "Kendall",
            isRefundable = false
        )
    }
}

@Preview
@Composable
fun PreviewTicketCardHeaderRefundable() {
    Surface {
        TicketCardHeader(
            centerIconImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA0MTZfMTA0%2FMDAxNTU1NDE1NTAzNTgx.n4hiEiGSF91TMegRtR5o3SA1RZbE6S6SnrnTGNNunlMg.PJoW33HktJZos6K3ENRRpZs3cNdYgSYv_3ph6RzIDx8g.JPEG.bemine9_9%2F0405_2_3.jpg&type=sc960_832",
            centerName = "Kendall",
            isRefundable = true
        )
    }
}

