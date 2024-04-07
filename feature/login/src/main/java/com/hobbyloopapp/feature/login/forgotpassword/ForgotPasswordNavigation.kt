package com.hobbyloopapp.feature.login.forgotpassword

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK

internal const val FORGOT_PASSWORD_ROUTE = "forgot_password"
private const val FORGOT_PASSWORD_DEEPLINK = "${ROOT_DEEPLINK}/forgot-password"

internal fun NavController.navigateToForgotPassword() {
    navigate(FORGOT_PASSWORD_ROUTE)
}

internal fun NavGraphBuilder.forgotPasswordScreen(onBackClick: () -> Unit) {
    composable(
        route = FORGOT_PASSWORD_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = FORGOT_PASSWORD_DEEPLINK }
        )
    ) {
        ForgotPasswordScreen(
            onBackClick = onBackClick,
        )
    }
}
