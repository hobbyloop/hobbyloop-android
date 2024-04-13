package com.hobbyloop.feature.reservation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val RESERVATION_ROUTE = "reservation"

internal fun NavGraphBuilder.reservationScreen(navController: NavController) {
    composable(route = RESERVATION_ROUTE) {
        ReservationScreen()
    }
}