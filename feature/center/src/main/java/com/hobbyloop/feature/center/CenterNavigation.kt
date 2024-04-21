package com.hobbyloop.feature.center

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val CENTER_ROUTE = "center"

internal fun NavGraphBuilder.centerScreen(backgroundColor: Color) {
    composable(route = CENTER_ROUTE) {
        CenterScreen(
            backgroundColor = backgroundColor,
        )
    }
}
