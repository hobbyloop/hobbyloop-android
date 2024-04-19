package com.hobbyloop.feature.schedule

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SCHEDULE_ROUTE = "storage"

internal fun NavGraphBuilder.scheduleScreen(onContentColor: (color: Color) -> Unit) {
    composable(route = SCHEDULE_ROUTE) {
        ScheduleScreen(
            backgroundColor = Color.Blue.copy(alpha = 0.1f),
            onContentColor = onContentColor,
        )
    }
}
