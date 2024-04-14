package com.hobbyloop.feature.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val LOGIN_ROUTE = "login"

internal fun NavGraphBuilder.loginScreen(onSignUpClick: () -> Unit) {
    composable(route = LOGIN_ROUTE) {
        LoginScreen(onSignUpClick = onSignUpClick)
    }
}