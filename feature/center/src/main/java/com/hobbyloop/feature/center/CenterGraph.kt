package com.hobbyloop.feature.center

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val CENTER_GRAPH_ROUTE = "center-graph"

fun NavGraphBuilder.centerGraph() {
    navigation(
        startDestination = CENTER_ROUTE,
        route = CENTER_GRAPH_ROUTE,
    ) {
        centerScreen()
    }
}