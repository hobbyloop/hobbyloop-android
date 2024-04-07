package com.hobbyloopapp.feature.reservation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK

const val RESERVATION_GRAPH_ROUTE = "reservation-graph"
private const val RESERVATION_DEEPLINK = "$ROOT_DEEPLINK/reservation"

fun NavGraphBuilder.reservationGraph(
) {
    navigation(
        startDestination = RESERVATION_ROUTE,
        route = RESERVATION_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = RESERVATION_DEEPLINK }
        ),
    ) {
        reservationScreen()
    }
}
