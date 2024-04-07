package com.hobbyloop.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hobbyloop.feature.home.onboarding.navigateToOnBoarding

internal const val HOME_ROUTE = "home"

internal fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(route = HOME_ROUTE) {
        HomeScreen(
            showOnBoarding = {
                navController.navigateToOnBoarding()
            },
        )
    }
}