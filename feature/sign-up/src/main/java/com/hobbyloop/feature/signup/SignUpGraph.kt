package com.hobbyloop.feature.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

internal const val SIGNUP_GRAPH_ROUTE = "signup_graph"

fun NavController.navigateToSignUpGraph() {
    navigate(SIGNUP_GRAPH_ROUTE)
}

fun NavGraphBuilder.signUpGraph(
    navController: NavController,
    onBackClick: () -> Unit,
    onNavigationBarClick: () -> Unit,
) {
    navigation(
        startDestination = SIGNUP_ROUTE,
        route = SIGNUP_GRAPH_ROUTE,
    ) {
        signUpScreen(
            onBackClick = onBackClick,
            onNavigationBarClick = onNavigationBarClick,
        )
    }
}