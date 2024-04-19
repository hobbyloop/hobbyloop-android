package com.hobbyloop.feature.schedule

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val STORAGE_GRAPH_ROUTE = "storage-graph"

fun NavGraphBuilder.storageGraph(
    onContentColor: (color: Color) -> Unit,
) {
    navigation(
        startDestination = SCHEDULE_ROUTE,
        route = STORAGE_GRAPH_ROUTE,
    ) {
        scheduleScreen(onContentColor = onContentColor)
    }
}
