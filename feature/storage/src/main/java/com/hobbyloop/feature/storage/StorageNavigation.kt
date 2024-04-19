package com.hobbyloop.feature.storage

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val STORAGE_ROUTE = "storage"

internal fun NavGraphBuilder.storageScreen(onContentColor: (color: Color) -> Unit) {
    composable(route = STORAGE_ROUTE) {
        StorageScreen(
            backgroundColor = Color.Blue.copy(alpha = 0.1f),
            onContentColor = onContentColor,
        )
    }
}
