package com.hobbyloop.core.ui.componenet.reservation.top_bar

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
import com.hobbyloop.core.ui.componenet.button.RoundRippleIcon
import com.hobbyloop.ui.R

@Composable
fun ReservationNavBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "앱 로고",
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.weight(1f))

        RoundRippleIcon(
            iconSize = 26.dp,
            overRipple = false,
            rippleColor = Color.Black,
            iconResId = R.drawable.ic_search,
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
            iconResId = R.drawable.ic_notice,
            contentDescription = "알림 확인",
            onClick = {
                onNotificationClick()
            }
        )
    }
}

@Preview
@Composable
fun PreviewReservationNavBar() {
    Surface {
        ReservationNavBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(17.dp),
            onSearchClick = { },
            onNotificationClick = { },
        )
    }
}
