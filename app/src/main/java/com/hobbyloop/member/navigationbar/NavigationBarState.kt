package com.hobbyloop.member.navigationbar

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Stable
internal class NavigationBarState(
    private val navController: NavController,
    val bottomBarScreens: List<BottomBarScreen>,
) {
    @SuppressLint("RestrictedApi")
    fun isRouteSelected(route: String): Flow<Boolean> {
        return navController.currentBackStack.map { backStack ->
            backStack
                .map { it.destination.route }
                .lastOrNull { currentRoute -> bottomBarScreens.any { screen -> screen.route == currentRoute } }
                .let { it == route }
        }
    }

    @SuppressLint("RestrictedApi")
    fun getCurrentScreen(): BottomBarScreen? {
        return navController.currentBackStack.value
            .mapNotNull { it.destination.route }
            .lastOrNull { currentRoute -> bottomBarScreens.any { screen -> screen.route == currentRoute } }
            ?.let { currentRoute -> bottomBarScreens.find { it.route == currentRoute } }
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
internal fun rememberNavigationBarState(
    navController: NavController,
    bottomBarScreenList: ImmutableList<BottomBarScreen>,
): NavigationBarState {
    return remember(navController, bottomBarScreenList) {
        NavigationBarState(navController, bottomBarScreenList)
    }
}
