package com.hobbyloop.feature.center

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val CENTER_GRAPH_ROUTE = "center-graph"

fun NavGraphBuilder.centerGraph(onContentColor: (color: Color) -> Unit) {
    navigation(
        startDestination = CENTER_ROUTE,
        route = CENTER_GRAPH_ROUTE,
    ) {
        centerScreen(onContentColor = onContentColor)
    }
}
