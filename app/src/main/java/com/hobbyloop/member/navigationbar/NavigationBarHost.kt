package com.hobbyloop.member.navigationbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hobbyloop.feature.center.centerGraph
import com.hobbyloop.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloop.feature.home.homeGraph

internal const val NAVIGATION_BAR_HOST_ROUTE = "navigation_bar_host"

fun NavController.navigateToNavigationBarHost() {
    navigate(NAVIGATION_BAR_HOST_ROUTE) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.navigationBarHost(navController: NavHostController) {
    composable(NAVIGATION_BAR_HOST_ROUTE) {
        Scaffold(
            bottomBar = {
                NavigationBar(navController = navController)
            },
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = HOME_GRAPH_ROUTE,
                route = NAVIGATION_BAR_HOST_ROUTE,
            ) {
                homeGraph(navController)
                centerGraph()
            }
        }
    }
}