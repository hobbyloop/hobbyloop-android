package com.hobbyloopapp.feature.my_page

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val MYPAGE_ROUTE = "mypage"

internal fun NavGraphBuilder.mypageScreen(
) {
    composable(route = MYPAGE_ROUTE) {
        MypageScreen()
    }
}
