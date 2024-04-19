package com.hobbyloop.feature.center

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val CENTER_ROUTE = "center"

internal fun NavGraphBuilder.centerScreen(onContentColor: (color: Color) -> Unit) {
    composable(route = CENTER_ROUTE) {
        CenterScreen(
            backgroundColor = Color.Gray.copy(alpha = 0.1f),
            onContentColor = onContentColor,
        )
    }
}
