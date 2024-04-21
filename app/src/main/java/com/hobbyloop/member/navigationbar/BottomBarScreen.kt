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

enum class BottomBarScreen(
    val route: String,
    @StringRes val title: Int,
    val iconLabelSpacing: Dp,
    @DrawableRes val unSelectedIcon: Int,
    val backgroundColor: Color,
) {
    HOME(HOME_GRAPH_ROUTE, R.string.bt_home, 6.dp, R.drawable.bt_home_ic, Color.Cyan.copy(alpha = 0.1f)),
    CENTER(CENTER_GRAPH_ROUTE, R.string.bt_center, 6.dp, R.drawable.bt_center_ic, Color.Gray.copy(alpha = 0.1f)),
    RESERVATION(RESERVATION_GRAPH_ROUTE, R.string.bt_reservation, 4.dp, R.drawable.bt_reservation_ic, Color.Red.copy(alpha = 0.1f)),
    SCHEDULE(STORAGE_GRAPH_ROUTE, R.string.bt_schedule, 6.dp, R.drawable.bt_schedule_ic, Color.Blue.copy(alpha = 0.1f)),
    MY(MY_PAGE_GRAPH_ROUTE, R.string.bt_my, 6.dp, R.drawable.bt_my_ic, Color.Yellow.copy(alpha = 0.1f)),
}
