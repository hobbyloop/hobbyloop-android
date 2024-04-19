package com.hobbyloop.feature.storage

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val STORAGE_GRAPH_ROUTE = "storage-graph"

fun NavGraphBuilder.storageGraph(
    navController: NavController,
    onContentColor: (color: Color) -> Unit,
) {
    navigation(
        startDestination = STORAGE_ROUTE,
        route = STORAGE_GRAPH_ROUTE,
    ) {
        storageScreen(onContentColor = onContentColor)
    }
}
