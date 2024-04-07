package com.hobbyloop.member.navigationbar

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.hobbyloop.feature.center.CENTER_GRAPH_ROUTE
import com.hobbyloop.feature.home.HOME_GRAPH_ROUTE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Stable
internal class NavigationBarState(private val navController: NavController) {
    private val navigationBarRoutes =
        listOf(
            HOME_GRAPH_ROUTE,
            CENTER_GRAPH_ROUTE,
        )

    @SuppressLint("RestrictedApi")
    fun isRouteSelected(route: String): Flow<Boolean> {
        return navController.currentBackStack.map { backStack ->
            backStack
                .map { it.destination.route }
                .lastOrNull { navigationBarRoutes.contains(it) }
                .let { it == route }
        }
    }

    fun openRoute(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
internal fun rememberNavigationBarState(navController: NavController): NavigationBarState {
    return remember(navController) {
        NavigationBarState(navController)
    }
}