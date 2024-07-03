package com.hobbyloop.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.hobbyloop.feature.home.onboarding.navigateToOnBoarding
import com.hobbyloop.feature.home.onboarding.onBoardingScreen

const val HOME_GRAPH_ROUTE = "home-graph"

fun NavGraphBuilder.homeGraph(
    navController: NavController,
) {
    navigation(
        startDestination = HOME_ROUTE,
        route = HOME_GRAPH_ROUTE,
    ) {
        homeScreen(
            showOnBoarding = {
                navController.navigateToOnBoarding()
            },
        )
        onBoardingScreen(
            onCloseClick = {
                navController.popBackStack()
            },
        )
    }
}
