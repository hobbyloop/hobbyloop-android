package com.hobbyloop.feature.center

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val CENTER_ROUTE = "center"

internal fun NavGraphBuilder.centerScreen() {
    composable(route = CENTER_ROUTE) {
        CenterScreen()
    }
}
