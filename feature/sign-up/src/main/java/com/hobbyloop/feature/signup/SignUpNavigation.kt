package com.hobbyloop.feature.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SIGNUP_ROUTE = "sign_up"

internal fun NavGraphBuilder.signUpScreen(
    onBackClick: () -> Unit,
    onNavigationBarClick: () -> Unit,
) {
    composable(route = SIGNUP_ROUTE) {
        SingUpScreen(
            onBackClick = onBackClick,
            onNavigationBarClick = onNavigationBarClick,
        )
    }
}