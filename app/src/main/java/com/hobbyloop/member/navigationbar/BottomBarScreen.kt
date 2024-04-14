package com.hobbyloop.member.navigationbar

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hobboyloopapp.feature.storage.STORAGE_GRAPH_ROUTE
import com.hobbyloop.member.R
import com.hobbyloopapp.feature.facility.FACILITY_GRAPH_ROUTE
import com.hobbyloopapp.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloopapp.feature.my_page.MYPAGE_GRAPH_ROUTE
import com.hobbyloopapp.feature.reservation.RESERVATION_GRAPH_ROUTE

/**
 * 추후 strings.xml에서 title 리소스 관리할 것
 */
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val iconLabelSpacing: Dp,
    @DrawableRes val unSelectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) {
    data object Home : BottomBarScreen(
        route = HOME_GRAPH_ROUTE,
        title = "홈",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_home_ic,
        selectedIcon = R.drawable.bt_home_filled_ic,
    )

    data object Facility : BottomBarScreen(
        route = FACILITY_GRAPH_ROUTE,
        title = "이용권",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_facility_ic,
        selectedIcon = R.drawable.bt_facility_filled_ic,
    )

    data object Reservation : BottomBarScreen(
        route = RESERVATION_GRAPH_ROUTE,
        title = "수업예약",
        iconLabelSpacing = 4.dp,
        unSelectedIcon = R.drawable.bt_calendar_ic,
        selectedIcon = R.drawable.bt_calendar_ic,
    )

    data object Storage : BottomBarScreen(
        route = STORAGE_GRAPH_ROUTE,
        title = "보관함",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_storage_ic,
        selectedIcon = R.drawable.bt_storage_filled_ic,
    )

    data object MyPage : BottomBarScreen(
        route = MYPAGE_GRAPH_ROUTE,
        title = "마이페이지",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_my_ic,
        selectedIcon = R.drawable.bt_my_filled_ic,
    )
}
