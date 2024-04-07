package com.hobbyloop.member.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.hobboyloopapp.feature.storage.STORAGE_GRAPH_ROUTE
import com.hobbyloopapp.feature.facility.FACILITY_GRAPH_ROUTE
import com.hobbyloopapp.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloopapp.feature.my_page.MYPAGE_GRAPH_ROUTE
import com.hobbyloopapp.feature.reservation.RESERVATION_GRAPH_ROUTE

@Composable
internal fun NavigationBar(navController: NavController) {
    val state = rememberNavigationBarState(navController)

    androidx.compose.material3.NavigationBar {
        val isHomeSelected by state.isRouteSelected(HOME_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isHomeSelected,
            onClick = {
                state.openRoute(HOME_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            }
        )

        val isCategoryBrowserSelected by state.isRouteSelected(FACILITY_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isCategoryBrowserSelected,
            onClick = {
                state.openRoute(FACILITY_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        )

        val isFavoritesSelected by state.isRouteSelected(RESERVATION_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isFavoritesSelected,
            onClick = {
                state.openRoute(RESERVATION_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }
        )

        val isCartSelected by state.isRouteSelected(STORAGE_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isCartSelected,
            onClick = {
                state.openRoute(STORAGE_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
            }
        )

        val isProfileSelected by state.isRouteSelected(MYPAGE_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isProfileSelected,
            onClick = {
                state.openRoute(MYPAGE_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            }
        )
    }
}
