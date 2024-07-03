package com.hobbyloop.member.root

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hobbyloop.feature.login.loginGraph
import com.hobbyloop.feature.signup.navigateToSignUpGraph
import com.hobbyloop.feature.signup.signUpGraph
import com.hobbyloop.member.navigationbar.navigateToNavigationBarHost
import com.hobbyloop.member.navigationbar.navigationBarHost

@Composable
internal fun RootHost(startDestination: String) {
    val rootController = rememberNavController()
    val navigationBarController = rememberNavController()

    NavHost(
        navController = rootController,
        startDestination = startDestination,
    ) {
        navigationBarHost(
            navController = navigationBarController,
        )
        loginGraph(
            onSignUpClick = { loginResponse -> rootController.navigateToSignUpGraph(loginResponse) },
        )
        signUpGraph(
            onBackClick = { rootController.popBackStack() },
            onNavigationBarClick = rootController::navigateToNavigationBarHost,
        )
    }
}
