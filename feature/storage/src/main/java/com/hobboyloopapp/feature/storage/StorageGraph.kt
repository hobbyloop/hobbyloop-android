package com.hobboyloopapp.feature.storage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK

const val STORAGE_GRAPH_ROUTE = "storage-graph"
private const val STORAGE_DEEPLINK = "$ROOT_DEEPLINK/storage"

fun NavGraphBuilder.storageGraph(
) {
    navigation(
        startDestination = STORAGE_ROUTE,
        route = STORAGE_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = STORAGE_DEEPLINK }
        ),
    ) {
        storageScreen()
    }
}
