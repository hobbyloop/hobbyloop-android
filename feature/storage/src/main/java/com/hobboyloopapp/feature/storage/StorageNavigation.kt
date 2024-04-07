package com.hobboyloopapp.feature.storage

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val STORAGE_ROUTE = "storage"

internal fun NavGraphBuilder.storageScreen(
) {
    composable(route = STORAGE_ROUTE) {
        StorageScreen()
    }
}

