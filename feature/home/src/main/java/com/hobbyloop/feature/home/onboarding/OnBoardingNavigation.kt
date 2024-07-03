package com.hobbyloop.feature.home.onboarding

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val ONBOARDING_ROUTE = "onboarding"

internal fun NavController.navigateToOnBoarding() {
    navigate(ONBOARDING_ROUTE)
}

internal fun NavGraphBuilder.onBoardingScreen(onCloseClick: () -> Unit) {
    dialog(
        route = ONBOARDING_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        OnBoardingScreen(
            onCloseClick = onCloseClick,
        )
    }
}