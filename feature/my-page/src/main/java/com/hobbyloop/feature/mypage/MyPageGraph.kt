package com.hobbyloop.feature.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val MY_PAGE_GRAPH_ROUTE = "my-page-graph"

fun NavGraphBuilder.myPageGraph(navController: NavController) {
    navigation(
        startDestination = MY_PAGE_ROUTE,
        route = MY_PAGE_GRAPH_ROUTE,
    ) {
        myPageScreen(navController)
    }
}