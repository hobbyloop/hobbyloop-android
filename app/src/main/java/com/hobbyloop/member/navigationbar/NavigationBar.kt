package com.hobbyloop.member.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hobbyloop.feature.center.CENTER_GRAPH_ROUTE
import com.hobbyloop.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloop.feature.mypage.MY_PAGE_GRAPH_ROUTE
import com.hobbyloop.feature.reservation.RESERVATION_GRAPH_ROUTE
import com.hobbyloop.feature.storage.STORAGE_GRAPH_ROUTE

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
            },
            label = {
                Text("홈")
            },
        )

        val isCategoryBrowserSelected by state.isRouteSelected(CENTER_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isCategoryBrowserSelected,
            onClick = {
                state.openRoute(CENTER_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            label = {
                Text("시설")
            },
        )

        val isReservationSelected by state.isRouteSelected(RESERVATION_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isReservationSelected,
            onClick = {
                state.openRoute(RESERVATION_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            },
            label = {
                Text("수업 예약")
            },
        )

        val isStorageSelected by state.isRouteSelected(STORAGE_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isStorageSelected,
            onClick = {
                state.openRoute(STORAGE_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
            label = {
                Text("보관함")
            },
        )

        val isMyPageSelected by state.isRouteSelected(MY_PAGE_GRAPH_ROUTE)
            .collectAsState(initial = false)
        NavigationBarItem(
            selected = isMyPageSelected,
            onClick = {
                state.openRoute(MY_PAGE_GRAPH_ROUTE)
            },
            icon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text("마이페이지")
            },
        )
    }
}

@Preview
@Composable
private fun NavigationBarPreview() {
    MaterialTheme {
        NavigationBar(rememberNavController())
    }
}