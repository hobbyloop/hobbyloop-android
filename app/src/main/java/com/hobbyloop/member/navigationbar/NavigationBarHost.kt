package com.hobbyloop.member.navigationbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.hobbyloop.feature.schedule.storageGraph

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
        var contentColor by remember { mutableStateOf(Color.White) }

        Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    roundedCornerShapeColor = contentColor,
                )
            },
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = HOME_GRAPH_ROUTE,
                route = NAVIGATION_BAR_HOST_ROUTE,
            ) {
                homeGraph(
                    navController = navController,
                    onContentColor = { color ->
                        contentColor = color
                    },
                )
                centerGraph(
                    onContentColor = { color ->
                        contentColor = color
                    },
                )
                reservationGraph(
                    onContentColor = { color ->
                        contentColor = color
                    },
                )
                storageGraph(
                    onContentColor = { color ->
                        contentColor = color
                    },
                )
                myPageGraph(
                    onContentColor = { color ->
                        contentColor = color
                    },
                )
            }
        }
    }
}
