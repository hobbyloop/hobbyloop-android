package com.hobbyloop.feature.reservation.detail

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val RESERVATION_DETAIL_GRAPH_ROUTE = "reservation-detail-graph"

fun NavGraphBuilder.reservationDetailGraph(
    backgroundColor: Color,
    onCloseClick: () -> Unit,
) {
    navigation(
        startDestination = RESERVATION_DETAIL_ROUTE,
        route = RESERVATION_DETAIL_GRAPH_ROUTE,
    ) {
        reservationDetailScreen(
            backgroundColor = backgroundColor,
            onCloseClick = onCloseClick
        )
    }
}
