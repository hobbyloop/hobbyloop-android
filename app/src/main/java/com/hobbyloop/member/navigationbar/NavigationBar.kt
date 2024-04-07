package com.hobbyloop.member.navigationbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hobboyloopapp.feature.storage.STORAGE_ROUTE
import com.hobbyloopapp.feature.facility.FACILITY_ROUTE
import com.hobbyloopapp.feature.home.HOME_ROUTE
import com.hobbyloopapp.feature.my_page.MYPAGE_ROUTE
import com.hobbyloopapp.feature.reservation.RESERVATION_ROUTE

@Composable
internal fun NavigationBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Facility,
        BottomBarScreen.Reservation,
        BottomBarScreen.Storage,
        BottomBarScreen.MyPage,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            val backStackEntry = navController.currentBackStackEntryAsState()
            screens.forEach { screen ->
                val currentRoute = backStackEntry.value?.destination?.route;
                val selected = currentRoute == screen.route
                AddItem(
                    screen = screen,
                    navController = navController,
                    selected = selected,
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavController,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    NavigationBarItem(
        label = {
            Text(
                if (selected) screen.title else "",
                fontWeight = FontWeight.SemiBold,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

/**
 * 추후 strings.xml에서 텍스트 리소스 관리할 것
 */
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = HOME_ROUTE,
        title = "홈",
        icon = Icons.Default.Home
    )

    data object Facility : BottomBarScreen(
        route = FACILITY_ROUTE,
        title = "이용권",
        icon = Icons.Default.Search
    )

    data object Reservation : BottomBarScreen(
        route = RESERVATION_ROUTE,
        title = "수업예약",
        icon = Icons.Default.AccountBox
    )

    data object Storage : BottomBarScreen(
        route = STORAGE_ROUTE,
        title = "보관함",
        icon = Icons.Default.ShoppingCart
    )

    data object MyPage : BottomBarScreen(
        route = MYPAGE_ROUTE,
        title = "마이페이지",
        icon = Icons.Default.Person
    )
}
