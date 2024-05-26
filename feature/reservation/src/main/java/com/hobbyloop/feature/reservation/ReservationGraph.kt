package com.hobbyloop.feature.reservation

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val RESERVATION_GRAPH_ROUTE = "reservation-graph"

fun NavGraphBuilder.reservationGraph(
    backgroundColor: Color,
    showReservationDetail: () -> Unit,
) {
    navigation(
        startDestination = RESERVATION_ROUTE,
        route = RESERVATION_GRAPH_ROUTE,
    ) {

        reservationScreen(
            backgroundColor = backgroundColor,
            showReservationDetail = showReservationDetail
        )
    }
}
