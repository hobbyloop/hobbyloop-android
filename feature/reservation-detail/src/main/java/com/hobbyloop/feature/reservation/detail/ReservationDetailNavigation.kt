package com.hobbyloop.feature.reservation.detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val RESERVATION_DETAIL_ROUTE = "reservation-detail"

fun NavController.navigateToReservationDetail() {
    navigate(RESERVATION_DETAIL_ROUTE)
}

internal fun NavGraphBuilder.reservationDetailScreen(
    backgroundColor: Color,
    onCloseClick: () -> Unit
) {
    dialog(
        route = RESERVATION_DETAIL_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        ReservationDetailScreen(
            backgroundColor = backgroundColor,
            onCloseClick = onCloseClick
        )
    }
}
