package com.hobbyloopapp.feature.facility

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val FACILITY_ROUTE = "facility"

internal fun NavGraphBuilder.facilityScreen(
) {
    composable(route = FACILITY_ROUTE) {
        FacilityScreen()
    }
}
