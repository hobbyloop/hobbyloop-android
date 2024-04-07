package com.hobbyloopapp.feature.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"

internal fun NavGraphBuilder.homeScreen(
    onFacilityClick: (facilityId: String) -> Unit,
) {
    composable(route = HOME_ROUTE) {
        val viewModel: HomeViewModel = hiltViewModel()
        val facilities = viewModel.facilities

        HomeScreen(
            facilities = facilities,
            onFacilityClick = onFacilityClick)
    }
}

