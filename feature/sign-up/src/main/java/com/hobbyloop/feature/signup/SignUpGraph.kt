package com.hobbyloop.feature.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.hobbyloop.domain.entity.login.UserLoginResult

internal const val SIGNUP_GRAPH_ROUTE = "signup_graph"

fun NavController.navigateToSignUpGraph(loginResponse: UserLoginResult) {
    navigate("$SIGNUP_ROUTE/${loginResponse.email}/${loginResponse.provider}/${loginResponse.subject}/${loginResponse.oauth2AccessToken}")
}

fun NavGraphBuilder.signUpGraph(
    onBackClick: () -> Unit,
    onNavigationBarClick: () -> Unit,
) {
    navigation(
        startDestination = "$SIGNUP_ROUTE/{email}/{provider}/{subject}/{oauth2AccessToken}",
        route = SIGNUP_GRAPH_ROUTE,
    ) {
        signUpScreen(
            onBackClick = onBackClick,
            onNavigationBarClick = onNavigationBarClick,
        )
    }
}
