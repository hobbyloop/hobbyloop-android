package com.hobbyloop.feature.reservation.ticket_detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

private const val TICKET_ID_ARG = "ticketId"
internal const val RESERVATION_TICKET_DETAIL_ROUTE = "reservation-ticket-detail/{$TICKET_ID_ARG}"

internal data class ReservationCenterDetailArgs(val ticketId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        ticketId = checkNotNull(savedStateHandle[TICKET_ID_ARG])
    )
}

fun NavController.navigateToReservationTicketDetail(ticketId: String) {
    navigate(RESERVATION_TICKET_DETAIL_ROUTE.replace("{$TICKET_ID_ARG}", ticketId))
}

internal fun NavGraphBuilder.reservationCenterDetailScreen(
    backgroundColor: Color,
    onCloseClick: () -> Unit
) {
    dialog(
        route = RESERVATION_TICKET_DETAIL_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        ReservationTicketDetailScreen(
            backgroundColor = backgroundColor,
            onCloseClick = onCloseClick
        )
    }
}
