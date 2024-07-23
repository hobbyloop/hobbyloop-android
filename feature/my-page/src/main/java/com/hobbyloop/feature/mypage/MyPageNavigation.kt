package com.hobbyloop.feature.mypage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val MY_PAGE_ROUTE = "my-page"

internal fun NavGraphBuilder.myPageScreen(
    onBackClick: () -> Unit,
    onSettingClick: () -> Unit,
    onEditMyInfoClick: () -> Unit,
    onMyPointClick: () -> Unit,
    onMyClassClick: () -> Unit,
    onMyTicketClick: () -> Unit,
    onMyCouponClick: () -> Unit,
    onMyBookmarkClick: () -> Unit,
) {
    composable(route = MY_PAGE_ROUTE) {
        MyPageScreen(
            onBackClick = onBackClick,
            onSettingClick = onSettingClick,
            onEditMyInfoClick = onEditMyInfoClick,
            onMyPointClick = onMyPointClick,
            onMyClassClick = onMyClassClick,
            onMyTicketClick = onMyTicketClick,
            onMyCouponClick = onMyCouponClick,
            onMyBookmarkClick = onMyBookmarkClick,
        )
    }
}
