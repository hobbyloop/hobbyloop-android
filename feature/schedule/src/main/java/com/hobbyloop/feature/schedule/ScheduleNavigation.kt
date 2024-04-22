package com.hobbyloop.feature.schedule

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SCHEDULE_ROUTE = "storage"

internal fun NavGraphBuilder.scheduleScreen(backgroundColor: Color) {
    composable(route = SCHEDULE_ROUTE) {
        ScheduleScreen(
            backgroundColor = backgroundColor,
        )
    }
}
