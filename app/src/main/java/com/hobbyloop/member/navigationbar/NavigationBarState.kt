package com.hobbyloop.member.navigationbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.hobboyloopapp.feature.storage.STORAGE_GRAPH_ROUTE
import com.hobbyloopapp.feature.facility.FACILITY_GRAPH_ROUTE
import com.hobbyloopapp.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloopapp.feature.my_page.MYPAGE_GRAPH_ROUTE
import com.hobbyloopapp.feature.reservation.RESERVATION_GRAPH_ROUTE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Stable
internal class NavigationBarState(private val navController: NavController) {
    val navigationBarRoutes = listOf(
        HOME_GRAPH_ROUTE,
        FACILITY_GRAPH_ROUTE,
        RESERVATION_GRAPH_ROUTE,
        STORAGE_GRAPH_ROUTE,
        MYPAGE_GRAPH_ROUTE,
    )

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
