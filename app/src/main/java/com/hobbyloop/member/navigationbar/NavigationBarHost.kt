package com.hobbyloop.member.navigationbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hobbyloop.feature.center.centerGraph
import com.hobbyloop.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloop.feature.home.homeGraph
import com.hobbyloop.feature.mypage.myPageGraph
import com.hobbyloop.feature.reservation.reservationGraph
import com.hobbyloop.feature.schedule.scheduleGraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

fun NavGraphBuilder.navigationBarHost(navController: NavHostController) {
    composable(NAVIGATION_BAR_HOST_ROUTE) {
        val scope = rememberCoroutineScope()

        var targetBackgroundColor by remember { mutableStateOf(BottomBarScreen.HOME.backgroundColor) }
        val backgroundColor by animateColorAsState(
            targetBackgroundColor,
            label = "animateBottomBarBackgroundColor",
        )

        Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    onBackgroundColor = { backgroundColor ->
                        scope.launch {
                            delay(100)
                            targetBackgroundColor = backgroundColor
                        }
                    },
                )
            },
            containerColor = backgroundColor,
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = HOME_GRAPH_ROUTE,
                route = NAVIGATION_BAR_HOST_ROUTE,
            ) {
                homeGraph(
                    navController = navController,
                    backgroundColor = backgroundColor,
                )
                centerGraph(
                    backgroundColor = backgroundColor,
                )
                reservationGraph(
                    backgroundColor = backgroundColor,
                )
                scheduleGraph(
                    backgroundColor = backgroundColor,
                )
                myPageGraph(
                    backgroundColor = backgroundColor,
                )
            }
        }
    }
}
