package com.hobbyloop.feature.reservation.reservation_completion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ReservationCompletionScreen(
    navigateToHomeScreen: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {

        }
    }
}
