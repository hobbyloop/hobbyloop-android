package com.hobbyloopapp.feature.login.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val LOGIN_ROUTE = "login"

internal fun NavGraphBuilder.loginScreen(
    onSignupClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoggedIn: () -> Unit,
) {
    composable(route = LOGIN_ROUTE) {
        LoginScreen(
            onLoginClick = onLoggedIn ,
            onNavigateToSignupScreen = onSignupClick,
            onForgotPasswordClick = onForgotPasswordClick,
        )
    }
}
