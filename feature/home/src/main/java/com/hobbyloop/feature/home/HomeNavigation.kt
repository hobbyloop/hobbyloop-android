package com.hobbyloop.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val HOME_ROUTE = "home"

internal fun NavGraphBuilder.homeScreen(
    showOnBoarding: () -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeScreen(
            showOnBoarding = showOnBoarding,
        )
    }
}
