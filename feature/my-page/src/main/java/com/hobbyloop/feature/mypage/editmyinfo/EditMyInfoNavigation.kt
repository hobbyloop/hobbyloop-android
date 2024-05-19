package com.hobbyloop.feature.mypage.editmyinfo

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val EDIT_MY_DATE_ROUTE = "edit-my-data"

internal fun NavController.navigateToEditMyInfo() {
    navigate(EDIT_MY_DATE_ROUTE)
}

internal fun NavGraphBuilder.editMyInfoScreen(onCloseClick: () -> Unit) {
    dialog(
        route = EDIT_MY_DATE_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        EditMyInfoScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
        )
    }
}
