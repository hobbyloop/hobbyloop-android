package com.hobbyloop.feature.reservation

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val RESERVATION_ROUTE = "reservation"

internal fun NavGraphBuilder.reservationScreen(onContentColor: (color: Color) -> Unit) {
    composable(route = RESERVATION_ROUTE) {
        ReservationScreen(
            backgroundColor = Color.Red.copy(alpha = 0.1f),
            onContentColor = onContentColor,
        )
    }
}
