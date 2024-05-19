package com.hobbyloop.feature.mypage.setting

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val MY_SETTING_ROUTE = "my-setting"

internal fun NavController.navigateToMySetting() {
    navigate(MY_SETTING_ROUTE)
}

internal fun NavGraphBuilder.mySettingScreen(onCloseClick: () -> Unit) {
    dialog(
        route = MY_SETTING_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MySettingScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
        )
    }
}
