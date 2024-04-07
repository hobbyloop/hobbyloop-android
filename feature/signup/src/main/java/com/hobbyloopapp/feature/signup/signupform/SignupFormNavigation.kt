package com.hobbyloopapp.feature.signup.signupform

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SIGNUP_ROUTE = "signup-form"

internal fun NavGraphBuilder.signupFormScreen(
    onNavigateBack: () -> Unit,
    onSignupClick: () -> Unit,
) {
    composable(SIGNUP_ROUTE) {
        SignUpFormScreen(
            onBackClick = onNavigateBack,
            onSignupClick = {
                onSignupClick()
            }
        )
    }
}
