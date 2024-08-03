package com.hobbyloop.feature.signup

import kotlinx.serialization.json.Json
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.hobbyloop.domain.entity.login.UserLoginResult
import kotlinx.serialization.encodeToString

internal const val SIGNUP_GRAPH_ROUTE = "signup_graph"

fun NavController.navigateToSignUpGraph(userLoginResult: UserLoginResult) {
    val userLoginResultJson = Json.encodeToString(userLoginResult)
    navigate("$SIGNUP_ROUTE/$userLoginResultJson")
}

fun NavGraphBuilder.signUpGraph(
    onBackClick: () -> Unit,
) {
    navigation(
        startDestination = "$SIGNUP_ROUTE/{userLoginResultJson}",
        route = SIGNUP_GRAPH_ROUTE,
    ) {
        signUpScreen(
            onBackClick = onBackClick,
        )
    }
}
