package com.hobbyloop.feature.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.hobbyloop.domain.entity.login.UserLoginResult

const val LOGIN_GRAPH_ROUTE = "login_graph"

fun NavController.navigateToLoginGraph() {
    navigate(LOGIN_GRAPH_ROUTE)
}

fun NavGraphBuilder.loginGraph(
    onSignUpClick: (UserLoginResult) -> Unit,
) {
    navigation(
        startDestination = LOGIN_ROUTE,
        route = LOGIN_GRAPH_ROUTE,
    ) {
        loginScreen(
            onSignUpClick = onSignUpClick,
        )
    }
}
