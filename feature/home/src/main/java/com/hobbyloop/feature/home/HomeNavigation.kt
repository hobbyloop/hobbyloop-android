package com.hobbyloop.feature.home

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val HOME_ROUTE = "home"

internal fun NavGraphBuilder.homeScreen(
    backgroundColor: Color,
    showOnBoarding: () -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeScreen(
            backgroundColor = backgroundColor,
            showOnBoarding = showOnBoarding,
        )
    }
}
