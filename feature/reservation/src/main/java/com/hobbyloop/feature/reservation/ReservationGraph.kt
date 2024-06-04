package com.hobbyloop.feature.reservation

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloop.feature.reservation.ticket_list.RESERVATION_TICKET_LIST_ROUTE
import com.hobbyloop.feature.reservation.ticket_list.reservationTicketListScreen
import com.hobbyloop.feature.reservation.center_detail.navigateToReservationCenterDetail
import com.hobbyloop.feature.reservation.center_detail.reservationCenterDetailScreen

const val RESERVATION_GRAPH_ROUTE = "reservation-graph"
private const val RESERVATION_DEEPLINK = "$ROOT_DEEPLINK/reservation"

fun NavGraphBuilder.reservationGraph(
    backgroundColor: Color,
    navController: NavHostController,
    onCloseClick: () -> Unit,
) {
    navigation(
        startDestination = RESERVATION_TICKET_LIST_ROUTE,
        route = RESERVATION_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = RESERVATION_DEEPLINK }
        ),
        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },
    ) {
        reservationTicketListScreen(
            backgroundColor = backgroundColor,
            navigateToReservationTicketDetail = { centerId ->
                navController.navigateToReservationCenterDetail(centerId)
            }
        )

        reservationCenterDetailScreen(
            backgroundColor = backgroundColor,
            onCloseClick = onCloseClick
        )
    }
}
