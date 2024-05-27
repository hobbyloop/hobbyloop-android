package com.hobbyloop.feature.reservation.center_list

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val RESERVATION_CENTER_LIST_ROUTE = "reservationCenterList"

internal fun NavGraphBuilder.reservationCenterListScreen(
    backgroundColor: Color,
    showReservationDetail: (centerId: String) -> Unit,
) {
    composable(route = RESERVATION_CENTER_LIST_ROUTE) {
        ReservationCenterListScreen(
            backgroundColor = backgroundColor,
            showReservationDetail = showReservationDetail
        )
    }
}
