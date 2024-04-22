package com.hobbyloop.feature.mypage

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val MY_PAGE_ROUTE = "my-page"

internal fun NavGraphBuilder.myPageScreen(backgroundColor: Color) {
    composable(route = MY_PAGE_ROUTE) {
        MyPageScreen(
            backgroundColor = backgroundColor,
        )
    }
}
