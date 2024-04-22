package com.hobbyloop.feature.schedule

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val SCHEDULE_GRAPH_ROUTE = "schedule-graph"

fun NavGraphBuilder.scheduleGraph(
    backgroundColor: Color,
) {
    navigation(
        startDestination = SCHEDULE_ROUTE,
        route = SCHEDULE_GRAPH_ROUTE,
    ) {
        scheduleScreen(backgroundColor = backgroundColor)
    }
}
