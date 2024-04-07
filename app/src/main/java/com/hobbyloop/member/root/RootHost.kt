package com.hobbyloop.member.root

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hobbyloop.member.navigationbar.navigateToNavigationBarHost
import com.hobbyloop.member.navigationbar.navigationBarHost
import com.hobbyloopapp.feature.login.LOGIN_GRAPH_ROUTE
import com.hobbyloopapp.feature.login.loginGraph
import com.hobbyloopapp.feature.signup.SIGNUP_GRAPH_ROUTE
import com.hobbyloopapp.feature.signup.navigateToSignupGraph
import com.hobbyloopapp.feature.signup.signupGraph

@Composable
fun RootHost() {
    val rootController = rememberNavController()
    val navigationBarController = rememberNavController()

    NavHost(
        navController = rootController,
        startDestination = LOGIN_GRAPH_ROUTE,
    ) {
        loginGraph(
            navController = rootController,
            onSignupClick = {
                rootController.navigateToSignupGraph()
            },
            onLoggedIn = {
                rootController.navigateToNavigationBarHost()
            }
        )
        signupGraph(
            navController = rootController,
            onSignupClick = {
                rootController.navigateToNavigationBarHost()
            }
        )
        navigationBarHost(navController = navigationBarController)
    }
}
