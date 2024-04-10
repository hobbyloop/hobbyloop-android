package com.hobbyloop.member.navigationbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hobboyloopapp.feature.storage.STORAGE_ROUTE
import com.hobbyloop.member.R
import com.hobbyloopapp.feature.facility.FACILITY_ROUTE
import com.hobbyloopapp.feature.home.HOME_ROUTE
import com.hobbyloopapp.feature.my_page.MYPAGE_ROUTE
import com.hobbyloopapp.feature.reservation.RESERVATION_ROUTE
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun NavigationBar(navController: NavController) {
    val screens =
        listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Facility,
            BottomBarScreen.Reservation,
            BottomBarScreen.Storage,
            BottomBarScreen.MyPage,
        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            screens.forEach { screen ->
                val currentRoute = navBackStackEntry?.destination?.route
                val selected = currentRoute == screen.route
                AddItem(
                    screen = screen,
                    navController = navController,
                    selected = selected,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavController,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    NavigationBarItem(
        label = {
            Text(
                if (selected) screen.title else "",
                fontWeight = FontWeight.SemiBold,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        icon = {
            Icon(
                painter =
                    if (selected) {
                        painterResource(id = screen.selectedIcon)
                    } else {
                        painterResource(
                            id = screen.unSelectedIcon,
                        )
                    },
                contentDescription = "Navigation Icon",
                tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors =
            NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
    )
}

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
        route = HOME_ROUTE,
        title = "홈",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_home_ic,
        selectedIcon = R.drawable.bt_home_filled_ic,
    )

    data object Facility : BottomBarScreen(
        route = FACILITY_ROUTE,
        title = "이용권",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_facility_ic,
        selectedIcon = R.drawable.bt_facility_filled_ic,
    )

    data object Reservation : BottomBarScreen(
        route = RESERVATION_ROUTE,
        title = "수업예약",
        iconLabelSpacing = 4.dp,
        unSelectedIcon = R.drawable.bt_calendar_ic,
        selectedIcon = R.drawable.bt_calendar_ic,
    )

    data object Storage : BottomBarScreen(
        route = STORAGE_ROUTE,
        title = "보관함",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_storage_ic,
        selectedIcon = R.drawable.bt_storage_filled_ic,
    )

    data object MyPage : BottomBarScreen(
        route = MYPAGE_ROUTE,
        title = "마이페이지",
        iconLabelSpacing = 6.dp,
        unSelectedIcon = R.drawable.bt_my_ic,
        selectedIcon = R.drawable.bt_my_filled_ic,
    )
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val screens =
        listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Facility,
            BottomBarScreen.Reservation,
            BottomBarScreen.Storage,
            BottomBarScreen.MyPage,
        ).toImmutableList()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val orangeColor = Color(0xFFFF5F05)

    Box(
        modifier
            .fillMaxWidth(),
    ) {
        BottomNavigationRow(
            navController = navController,
            screens = screens,
            currentRoute = currentRoute,
            selectedColor = Color.Black,
            unselectedColor = Color.Gray,
            labelSize = 12.sp,
            iconSize = 24.dp,
        )
        FloatingActionIconButton(
            navController = navController,
            size = 50.dp,
            backgroundColor = orangeColor,
            iconId = R.drawable.bt_calendar_ic,
            iconTint = Color.White,
            contentDescription = "CalendarButton",
            yOffset = (-57).dp,
            route = BottomBarScreen.Reservation.route,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun BottomNavigationRow(
    navController: NavController,
    screens: ImmutableList<BottomBarScreen>,
    currentRoute: String?,
    selectedColor: Color,
    unselectedColor: Color,
    labelSize: TextUnit,
    iconSize: Dp,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Gray,
    borderWidth: Dp = 0.3.dp,
    height: Dp = 92.dp,
    bottomPadding: Dp = 20.dp,
    horizontalPadding: Dp = 10.dp,
    applyRoundedCorners: Boolean = true,
    cornerRadius: Dp = 30.dp,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(height)
                .background(backgroundColor)
                .border(
                    borderWidth,
                    borderColor,
                    if (applyRoundedCorners) {
                        RoundedCornerShape(
                            topStart = cornerRadius,
                            topEnd = cornerRadius,
                        )
                    } else {
                        RectangleShape
                    },
                )
                .padding(bottom = bottomPadding, start = horizontalPadding, end = horizontalPadding),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        screens.forEach { screen ->
            ScreenContent(
                screen = screen,
                isSelected = currentRoute == screen.route,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                labelSize = labelSize,
                iconSize = iconSize,
                modifier = Modifier.weight(1f),
            ) {
                navigateToScreen(
                    navController = navController,
                    route = screen.route,
                )
            }
        }
    }
}

@Composable
fun ScreenContent(
    screen: BottomBarScreen,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    labelSize: TextUnit,
    iconSize: Dp,
    modifier: Modifier = Modifier,
    onScreenClicked: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        onScreenClicked()
                    })
                },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(screen.iconLabelSpacing),
    ) {
        if (screen != BottomBarScreen.Reservation) {
            Icon(
                painter =
                    if (isSelected) {
                        painterResource(id = screen.selectedIcon)
                    } else {
                        painterResource(
                            id = screen.unSelectedIcon,
                        )
                    },
                contentDescription = screen.title,
                tint = if (isSelected) selectedColor else unselectedColor,
                modifier = Modifier.size(iconSize),
            )
        } else {
            Spacer(modifier = Modifier.size(iconSize))
        }
        Text(
            text = screen.title,
            color = if (isSelected) selectedColor else unselectedColor,
            fontSize = labelSize,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        )
    }
}

@Composable
fun FloatingActionIconButton(
    navController: NavController,
    size: Dp,
    backgroundColor: Color,
    iconId: Int,
    iconTint: Color,
    yOffset: Dp,
    route: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = "",
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .offset(y = yOffset)
                .size(size)
                .clip(CircleShape)
                .background(backgroundColor)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        navigateToScreen(navController, route)
                    })
                },
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = iconTint,
        )
    }
}

fun navigateToScreen(
    navController: NavController,
    route: String,
) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
