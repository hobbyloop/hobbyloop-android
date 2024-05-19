package com.hobbyloop.feature.mypage.myclass

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val MY_CLASS_ROUTE = "my-class"

internal fun NavController.navigateToMyClass() {
    navigate(MY_CLASS_ROUTE)
}

internal fun NavGraphBuilder.myClassScreen(
    onCloseClick: () -> Unit,
    onMyClassDetail: () -> Unit
) {
    dialog(
        route = MY_CLASS_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MyClassScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
            onMyClassDetail = onMyClassDetail

        )
    }
}
