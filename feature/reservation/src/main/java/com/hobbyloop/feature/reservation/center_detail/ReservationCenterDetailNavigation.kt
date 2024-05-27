package com.hobbyloop.feature.reservation.center_detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

private const val CENTER_ID_ARG = "centerId"
internal const val RESERVATION_CENTER_DETAIL_ROUTE = "reservation-center-detail/{$CENTER_ID_ARG}"

internal data class ReservationCenterDetailArgs(val centerId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        centerId = checkNotNull(savedStateHandle[CENTER_ID_ARG])
    )
}

fun NavController.navigateToReservationCenterDetail(centerId: String) {
    navigate(RESERVATION_CENTER_DETAIL_ROUTE.replace("{$CENTER_ID_ARG}", centerId))
}

internal fun NavGraphBuilder.reservationCenterDetailScreen(
    backgroundColor: Color,
    onCloseClick: () -> Unit
) {
    dialog(
        route = RESERVATION_CENTER_DETAIL_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        ReservationCenterDetailScreen(
            backgroundColor = backgroundColor,
            onCloseClick = onCloseClick
        )
    }
}
