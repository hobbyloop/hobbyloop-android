package com.hobbyloopapp.feature.facility

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK

const val FACILITY_GRAPH_ROUTE = "facility-graph"
private const val FACILITY_DEEPLINK = "$ROOT_DEEPLINK/facility"

fun NavGraphBuilder.facilityGraph(
) {
    navigation(
        startDestination = FACILITY_ROUTE,
        route = FACILITY_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = FACILITY_DEEPLINK }
        ),
    ) {
        facilityScreen()
    }
}
