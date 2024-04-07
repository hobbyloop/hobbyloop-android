package com.hobbyloopapp.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK

const val HOME_GRAPH_ROUTE = "home-graph"
private const val HOME_DEEPLINK = "$ROOT_DEEPLINK/home"

fun NavGraphBuilder.homeGraph(
    onFacilityClick: (facilityId: String) -> Unit,
) {
    navigation(
        startDestination = HOME_ROUTE,
        route = HOME_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = HOME_DEEPLINK }
        ),
    ) {
        homeScreen(onFacilityClick = onFacilityClick)
    }
}
