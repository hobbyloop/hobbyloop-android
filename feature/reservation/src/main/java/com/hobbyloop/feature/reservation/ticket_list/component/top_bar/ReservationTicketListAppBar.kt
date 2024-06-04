package com.hobbyloop.feature.reservation.ticket_list.component.top_bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.component.button.RoundRippleIcon

@Composable
fun ReservationTicketListAppBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.app_logo_ic),
            contentDescription = "앱 로고"
        )

        Spacer(modifier = Modifier.weight(1f))

        RoundRippleIcon(
            iconSize = 26.dp,
            overRipple = false,
            rippleColor = Color.Black,
            iconResId = R.drawable.search_ic,
            contentDescription = "검색",
            onClick = {
                onSearchClick()
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        RoundRippleIcon(
            iconSize = 26.dp,
            overRipple = false,
            rippleColor = Color.Black,
            iconResId = R.drawable.notification_ic,
            contentDescription = "알림 확인",
            onClick = {
                onNotificationClick()
            }
        )
    }
}

@Preview
@Composable
fun PreviewReservationTicketListAppBar() {
    Surface {
        ReservationTicketListAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(17.dp),
            onSearchClick = { },
            onNotificationClick = { },
        )
    }
}
