package com.hobbyloopapp.feature.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK
import com.hobbyloopapp.feature.signup.signupform.SIGNUP_ROUTE
import com.hobbyloopapp.feature.signup.signupform.signupFormScreen

const val SIGNUP_GRAPH_ROUTE = "signup-graph"
private const val SIGNUP_DEEPLINK = "$ROOT_DEEPLINK/signup"

fun NavController.navigateToSignupGraph() {
    navigate(SIGNUP_GRAPH_ROUTE)
}

fun NavGraphBuilder.signupGraph(
    navController: NavController,
    onSignupClick: () -> Unit
) {
    navigation(
        startDestination = SIGNUP_ROUTE,
        route = SIGNUP_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = SIGNUP_DEEPLINK }
        )
    ) {
        signupFormScreen(
            onNavigateBack = {
                navController.popBackStack()
            },
            onSignupClick = {
                onSignupClick()
            }
        )
    }
}
