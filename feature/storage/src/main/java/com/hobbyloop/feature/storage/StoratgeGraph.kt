package com.hobbyloop.feature.storage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val STORAGE_GRAPH_ROUTE = "storage-graph"

fun NavGraphBuilder.storageGraph(navController: NavController) {
    navigation(
        startDestination = STORAGE_ROUTE,
        route = STORAGE_GRAPH_ROUTE,
    ) {
        reservationScreen(navController)
    }
}