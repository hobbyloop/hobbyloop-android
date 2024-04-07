package com.hobbyloopapp.feature.reservation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val RESERVATION_ROUTE = "reservation"

internal fun NavGraphBuilder.reservationScreen(
) {
    composable(route = RESERVATION_ROUTE) {
        ReservationScreen()
    }
}

