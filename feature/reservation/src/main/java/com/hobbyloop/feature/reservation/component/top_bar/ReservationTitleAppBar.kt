package com.hobbyloop.feature.reservation.component.top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReservationTitleAppBar(
    title: String,
    onCloseClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .clickable {
                    onCloseClick()
                }
        )
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PreviewReservationTitleAppBar() {
    Surface {
        ReservationTitleAppBar(
            title = "수업예약",
            onCloseClick = { },
        )
    }
}
