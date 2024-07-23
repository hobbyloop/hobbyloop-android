package com.hobbyloop.feature.mypage.myclassdetail

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val MY_CLASS_DETAIL_ROUTE = "my-class-detail"

internal fun NavController.navigateToMyClassDetail() {
    navigate(MY_CLASS_DETAIL_ROUTE)
}

internal fun NavGraphBuilder.myClassDetailScreen(onCloseClick: () -> Unit) {
    dialog(
        route = MY_CLASS_DETAIL_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MyClassDetailScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
        )
    }
}
