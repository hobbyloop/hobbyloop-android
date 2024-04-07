package com.hobbyloopapp.feature.my_page

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MYPAGE_ROUTE = "mypage"

internal fun NavGraphBuilder.mypageScreen(
) {
    composable(route = MYPAGE_ROUTE) {
        MypageScreen()
    }
}
