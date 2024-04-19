package com.hobbyloop.feature.home

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val HOME_ROUTE = "home"

internal fun NavGraphBuilder.homeScreen(
    onContentColor: (color: Color) -> Unit,
    showOnBoarding: () -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeScreen(
            backgroundColor = Color.Cyan.copy(alpha = 0.1f),
            onContentColor = onContentColor,
            showOnBoarding = showOnBoarding,
        )
    }
}
