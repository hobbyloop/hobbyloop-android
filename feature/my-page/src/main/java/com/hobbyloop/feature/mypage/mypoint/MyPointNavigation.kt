package com.hobbyloop.feature.mypage.mypoint

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val MY_POINT_ROUTE = "my-point"

internal fun NavController.navigateToMyPoint() {
    navigate(MY_POINT_ROUTE)
}

internal fun NavGraphBuilder.myPointScreen(onCloseClick: () -> Unit) {
    dialog(
        route = MY_POINT_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MyPointScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
        )
    }
}
