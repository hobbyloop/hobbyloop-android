package com.hobbyloop.feature.reservation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun ReservationDetailScreen(
    backgroundColor: Color,
    onCloseClick: () -> Unit,
) {
    Scaffold { padding ->
        Column(
            modifier =
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor),
        ) {
            Text(
                text = "ReservationDetailScreen - 아직 미구현",
            )
        }
    }
}

