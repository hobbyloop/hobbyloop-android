package com.hobbyloop.feature.schedule

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val SCHEDULE_GRAPH_ROUTE = "schedule-graph"

fun NavGraphBuilder.scheduleGraph() {
    navigation(
        startDestination = SCHEDULE_ROUTE,
        route = SCHEDULE_GRAPH_ROUTE,
    ) {
        scheduleScreen()
    }
}
