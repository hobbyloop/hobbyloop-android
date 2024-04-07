package com.hobboyloopapp.feature.storage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val STORAGE_ROUTE = "storage"

internal fun NavGraphBuilder.storageScreen(
) {
    composable(route = STORAGE_ROUTE) {
        StorageScreen()
    }
}

