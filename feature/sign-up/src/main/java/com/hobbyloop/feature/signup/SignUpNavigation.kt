package com.hobbyloop.feature.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hobbyloop.domain.entity.login.UserLoginResult

internal const val SIGNUP_ROUTE = "sign_up"

internal fun NavGraphBuilder.signUpScreen(
    onBackClick: () -> Unit,
    onNavigationBarClick: () -> Unit,
) {
    composable(
        route = "$SIGNUP_ROUTE/{email}/{provider}/{subject}/{oauth2AccessToken}",
        arguments = listOf(
            navArgument("email") { type = NavType.StringType },
            navArgument("provider") { type = NavType.StringType },
            navArgument("subject") { type = NavType.StringType },
            navArgument("oauth2AccessToken") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val email = backStackEntry.arguments?.getString("email") ?: ""
        val provider = backStackEntry.arguments?.getString("provider") ?: ""
        val subject = backStackEntry.arguments?.getString("subject") ?: ""
        val oauth2AccessToken = backStackEntry.arguments?.getString("oauth2AccessToken") ?: ""

        val userLoginResult = UserLoginResult(
            accessToken = null,
            refreshToken = null,
            email = email,
            provider = provider,
            subject = subject,
            oauth2AccessToken = oauth2AccessToken
        )

        SignUpScreen(
            onBackClick = onBackClick,
            onNavigationBarClick = onNavigationBarClick,
            userLoginResult = userLoginResult,
        )
    }
}
