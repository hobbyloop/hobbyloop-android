package com.hobbyloop.member.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.hobbyloop.member.R

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val orangeColor = Color(0xFFFF5F05)

    val state = rememberNavigationBarState(navController)

    Box(
        modifier
            .fillMaxWidth(),
    ) {
        BottomNavigationRow(
            state = state,
            selectedColor = Color.Black,
            unselectedColor = Color.Gray,
            labelSize = 12.sp,
            iconSize = 24.dp,
        )
        FloatingActionIconButton(
            state = state,
            route = BottomBarScreen.Reservation.route,
            size = 50.dp,
            backgroundColor = orangeColor,
            iconId = R.drawable.bt_calendar_ic,
            iconTint = Color.White,
            contentDescription = "CalendarButton",
            yOffset = (-57).dp,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun BottomNavigationRow(
    state: NavigationBarState,
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
        state.bottomBarScreens.forEach { screen ->
            val isSelected by state.isRouteSelected(screen.route).collectAsState(initial = false)

            ScreenContent(
                screen = screen,
                isSelected = isSelected,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                labelSize = labelSize,
                iconSize = iconSize,
                modifier = Modifier.weight(1f),
            ) {
                state.openRoute(screen.route)
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
private fun FloatingActionIconButton(
    state: NavigationBarState,
    route: String,
    size: Dp,
    backgroundColor: Color,
    iconId: Int,
    iconTint: Color,
    yOffset: Dp,
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
                        state.openRoute(route)
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



