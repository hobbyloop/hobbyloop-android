package com.hobbyloop.feature.mypage.mybookmark

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val MY_BOOKMARK_ROUTE = "my-bookmark"

internal fun NavController.navigateToMyBookmark() {
    navigate(MY_BOOKMARK_ROUTE)
}

internal fun NavGraphBuilder.myBookmarkScreen(onCloseClick: () -> Unit) {
    dialog(
        route = MY_BOOKMARK_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MyBookmarkScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
        )
    }
}
