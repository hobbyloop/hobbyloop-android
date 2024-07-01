package com.hobbyloop.member.navigationbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.center.CENTER_GRAPH_ROUTE
import com.hobbyloop.feature.home.HOME_GRAPH_ROUTE
import com.hobbyloop.feature.mypage.MY_PAGE_GRAPH_ROUTE
import com.hobbyloop.feature.reservation.RESERVATION_GRAPH_ROUTE
import com.hobbyloop.feature.schedule.SCHEDULE_GRAPH_ROUTE
import com.hobbyloop.member.R

enum class BottomBarScreen(
    val route: String,
    @StringRes val title: Int,
    val iconLabelSpacing: Dp,
    @DrawableRes val unSelectedIcon: Int,
) {
    HOME(HOME_GRAPH_ROUTE, R.string.bt_home, 6.dp, R.drawable.bt_home_ic),
    CENTER(CENTER_GRAPH_ROUTE, R.string.bt_center, 6.dp, R.drawable.bt_center_ic),
    RESERVATION(RESERVATION_GRAPH_ROUTE, R.string.bt_reservation, 4.dp, R.drawable.bt_reservation_ic),
    SCHEDULE(SCHEDULE_GRAPH_ROUTE, R.string.bt_schedule, 6.dp, R.drawable.bt_schedule_ic),
    MY(MY_PAGE_GRAPH_ROUTE, R.string.bt_my, 6.dp, R.drawable.bt_my_ic),
}
