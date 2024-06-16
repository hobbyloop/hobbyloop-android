package com.hobbyloop.feature.schedule

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SCHEDULE_ROUTE = "storage"

internal fun NavGraphBuilder.scheduleScreen() {
    composable(route = SCHEDULE_ROUTE) {
        ScheduleScreen()
    }
}
