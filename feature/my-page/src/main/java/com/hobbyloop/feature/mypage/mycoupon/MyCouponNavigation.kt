package com.hobbyloop.feature.mypage.mycoupon

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val MY_COUPON_ROUTE = "my-coupon"

internal fun NavController.navigateToMyCoupon() {
    navigate(MY_COUPON_ROUTE)
}

internal fun NavGraphBuilder.myCouponScreen(onCloseClick: () -> Unit) {
    dialog(
        route = MY_COUPON_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MyCouponScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
        )
    }
}
