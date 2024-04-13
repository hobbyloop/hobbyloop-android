package com.hobbyloop.feature.storage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val STORAGE_ROUTE = "storage"

internal fun NavGraphBuilder.reservationScreen(navController: NavController) {
    composable(route = STORAGE_ROUTE) {
        StorageScreen()
    }
}