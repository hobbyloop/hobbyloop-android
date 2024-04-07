package com.hobbyloop.member.navigationbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hobboyloopapp.feature.storage.storageGraph
import com.hobbyloopapp.feature.facility.facilityGraph
import com.hobbyloopapp.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloopapp.feature.home.homeGraph
import com.hobbyloopapp.feature.login.LOGIN_GRAPH_ROUTE
import com.hobbyloopapp.feature.my_page.mypageGraph
import com.hobbyloopapp.feature.reservation.reservationGraph
import com.hobbyloopapp.feature.signup.SIGNUP_GRAPH_ROUTE

internal const val NAVIGATION_BAR_HOST_ROUTE = "navigation_bar_host"

fun NavController.navigateToNavigationBarHost() {
    navigate(NAVIGATION_BAR_HOST_ROUTE) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

internal fun NavGraphBuilder.navigationBarHost(
    navController: NavHostController
) {
    composable(NAVIGATION_BAR_HOST_ROUTE) { entry ->
        Scaffold(
            bottomBar = {
                NavigationBar(navController = navController)
            },
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = HOME_GRAPH_ROUTE,
                modifier = Modifier.padding(padding),
            ) {
                homeGraph()
                facilityGraph()
                reservationGraph()
                storageGraph()
                mypageGraph()
            }
        }
    }
}
