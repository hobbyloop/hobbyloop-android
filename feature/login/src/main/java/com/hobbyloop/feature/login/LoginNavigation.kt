package com.hobbyloop.feature.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hobbyloop.domain.entity.login.UserLoginResult

internal const val LOGIN_ROUTE = "login"

internal fun NavGraphBuilder.loginScreen(onSignUpClick: (UserLoginResult) -> Unit) {
    composable(route = LOGIN_ROUTE) {
        LoginScreen(onSignUpClick = onSignUpClick)
    }
}