package com.hobbyloop.feature.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hobbyloop.domain.entity.login.UserLoginResult
import kotlinx.serialization.json.Json

internal const val SIGNUP_ROUTE = "sign_up"

internal fun NavGraphBuilder.signUpScreen(
    onBackClick: () -> Unit,
) {
    composable("$SIGNUP_ROUTE/{userLoginResultJson}") { backStackEntry ->
        val userLoginResultJson = backStackEntry.arguments?.getString("userLoginResultJson")
        val userLoginResult = userLoginResultJson?.let { Json.decodeFromString<UserLoginResult>(it) }
        if (userLoginResult == null) onBackClick()
        SignUpScreen(
            userLoginResult = userLoginResult!!,
            onBackClick = onBackClick,
        )
    }
}
