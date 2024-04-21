package com.hobbyloop.member.navigationbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.center.CENTER_GRAPH_ROUTE
import com.hobbyloop.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloop.feature.mypage.MY_PAGE_GRAPH_ROUTE
import com.hobbyloop.feature.reservation.RESERVATION_GRAPH_ROUTE
import com.hobbyloop.feature.schedule.STORAGE_GRAPH_ROUTE
import com.hobbyloop.member.R

sealed class BottomBarScreen(
    val route: String,
    @StringRes val title: Int,
    val iconLabelSpacing: Dp,
    @DrawableRes val unSelectedIcon: Int,
    val backgroundColor: Color,
) {
    data object Home : BottomBarScreen(
        route = HOME_GRAPH_ROUTE,
        title = R.string.bt_home,
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_home_ic,
        backgroundColor = Color.Cyan.copy(alpha = 0.1f),
    )

    data object Facility : BottomBarScreen(
        route = CENTER_GRAPH_ROUTE,
        title = R.string.bt_center,
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_center_ic,
        backgroundColor = Color.Gray.copy(alpha = 0.1f),
    )

    data object Reservation : BottomBarScreen(
        route = RESERVATION_GRAPH_ROUTE,
        title = R.string.bt_reservation,
        iconLabelSpacing = 4.dp,
        unSelectedIcon = R.drawable.bt_reservation_ic,
        backgroundColor = Color.Red.copy(alpha = 0.1f),
    )

    data object Schedule : BottomBarScreen(
        route = STORAGE_GRAPH_ROUTE,
        title = R.string.bt_schedule,
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_schedule_ic,
        backgroundColor = Color.Blue.copy(alpha = 0.1f),
    )

    data object My : BottomBarScreen(
        route = MY_PAGE_GRAPH_ROUTE,
        title = R.string.bt_my,
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_my_ic,
        backgroundColor = Color.Yellow.copy(alpha = 0.1f),
    )
}
