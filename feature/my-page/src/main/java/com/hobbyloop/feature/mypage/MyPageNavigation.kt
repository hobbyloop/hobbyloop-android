package com.hobbyloop.feature.mypage

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val MY_PAGE_ROUTE = "my-page"

internal fun NavGraphBuilder.myPageScreen(onContentColor: (color: Color) -> Unit) {
    composable(route = MY_PAGE_ROUTE) {
        MyPageScreen(
            backgroundColor = Color.Yellow.copy(alpha = 0.1f),
            onContentColor = onContentColor,
        )
    }
}
