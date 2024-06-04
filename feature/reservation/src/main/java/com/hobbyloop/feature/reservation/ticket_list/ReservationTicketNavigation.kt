package com.hobbyloop.feature.reservation.ticket_list

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val RESERVATION_TICKET_LIST_ROUTE = "reservationTicketList"

internal fun NavGraphBuilder.reservationTicketListScreen(
    backgroundColor: Color,
    navigateToReservationTicketDetail: (centerId: String) -> Unit,
) {
    composable(route = RESERVATION_TICKET_LIST_ROUTE) {
        ReservationTicketListScreen(
            navigateToReservationTicketDetail = navigateToReservationTicketDetail
        )
    }
}
