package com.hobbyloop.feature.reservation

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloop.feature.reservation.reservation_detail.navigateToReservationDetail
import com.hobbyloop.feature.reservation.reservation_detail.reservationDetailScreen
import com.hobbyloop.feature.reservation.reservation_completion.navigateToReservationCompletion
import com.hobbyloop.feature.reservation.reservation_completion.reservationCompletionScreen
import com.hobbyloop.feature.reservation.ticket_list.RESERVATION_TICKET_LIST_ROUTE
import com.hobbyloop.feature.reservation.ticket_list.reservationTicketListScreen
import com.hobbyloop.feature.reservation.ticket_detail.navigateToReservationTicketDetail
import com.hobbyloop.feature.reservation.ticket_detail.reservationCenterDetailScreen

const val RESERVATION_GRAPH_ROUTE = "reservation-graph"
private const val RESERVATION_DEEPLINK = "$ROOT_DEEPLINK/reservation"

fun NavGraphBuilder.reservationGraph(
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
            navigateToReservationTicketDetail = { ticketId ->
                navController.navigateToReservationTicketDetail(ticketId)
            }
        )

        reservationCenterDetailScreen(
            onCloseClick = onCloseClick,
            navigateToReservationClassDetail = { classId ->
                navController.navigateToReservationDetail(classId)
            }
        )

        reservationDetailScreen(
            onCloseClick = onCloseClick,
            navigateToReservationCompletion = { classId ->
                navController.navigateToReservationCompletion(classId)
            }
        )

        reservationCompletionScreen(
            navigateToReservationHome = {
                navController.navigate(RESERVATION_TICKET_LIST_ROUTE) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
