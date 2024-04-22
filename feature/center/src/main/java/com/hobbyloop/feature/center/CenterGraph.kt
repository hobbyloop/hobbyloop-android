package com.hobbyloop.feature.center

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val CENTER_GRAPH_ROUTE = "center-graph"

fun NavGraphBuilder.centerGraph(backgroundColor: Color) {
    navigation(
        startDestination = CENTER_ROUTE,
        route = CENTER_GRAPH_ROUTE,
    ) {
        centerScreen(
            backgroundColor = backgroundColor,
        )
    }
}
