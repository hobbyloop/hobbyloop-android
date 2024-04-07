package com.hobbyloopapp.feature.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK
import com.hobbyloopapp.feature.login.forgotpassword.forgotPasswordScreen
import com.hobbyloopapp.feature.login.forgotpassword.navigateToForgotPassword
import com.hobbyloopapp.feature.login.login.LOGIN_ROUTE
import com.hobbyloopapp.feature.login.login.loginScreen

const val LOGIN_GRAPH_ROUTE = "login_graph"
private const val LOGIN_DEEPLINK = "$ROOT_DEEPLINK/login"

fun NavController.navigateToLoginGraph() {
    navigate(LOGIN_GRAPH_ROUTE)
}

fun NavGraphBuilder.loginGraph(
    navController: NavController,
    onSignupClick: () -> Unit,
    onLoggedIn: () -> Unit
) {

    navigation(
        startDestination = LOGIN_ROUTE,
        route = LOGIN_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = LOGIN_DEEPLINK }
        )
    ) {
        loginScreen(
            onSignupClick = onSignupClick,
            onForgotPasswordClick = {
                navController.navigateToForgotPassword()
            },
            onLoggedIn = {
                onLoggedIn()
            }
        )
        forgotPasswordScreen(
            onBackClick = {
                navController.popBackStack()
            },
        )
    }
}
