package com.hobbyloop.member.navigationbar

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hobbyloop.member.R
import kotlinx.collections.immutable.toImmutableList
import theme.HobbyLoopColor

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val bottomBarScreenList = BottomBarScreen.entries.toImmutableList()

    val state =
        rememberNavigationBarState(
            navController = navController,
            bottomBarScreenList = bottomBarScreenList,
        )
    var isCenterTabClicked by remember { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        BottomNavigationRow(
            state = state,
            selectedColor = Color.Black,
            unselectedColor = HobbyLoopColor.Gray40,
            labelSize = 12.sp,
            iconSize = 24.dp,
            onCenterTabClicked = { clicked ->
                isCenterTabClicked = clicked
            },
        )
        FloatingActionIconButton(
            state = state,
            isSelected = isCenterTabClicked,
            selectedColor = Color.Black,
            unselectedColor = Color.White,
            route = BottomBarScreen.RESERVATION.route,
            size = 50.dp,
            iconId = R.drawable.bt_reservation_ic,
            yOffset = (-57).dp,
            modifier = Modifier.align(Alignment.BottomCenter),
            contentDescription = "ReservationButton",
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
    onCenterTabClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    shadowElevation: Dp = 15.dp,
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
                .shadow(
                    elevation = shadowElevation,
                    shape =
                        if (applyRoundedCorners) {
                            RoundedCornerShape(
                                topStart = cornerRadius,
                                topEnd = cornerRadius,
                            )
                        } else {
                            RectangleShape
                        },
                )
                .background(backgroundColor)
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
                onCenterTabClicked = onCenterTabClicked,
                onScreenClicked = { state.openRoute(screen.route) },
            )
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
    onCenterTabClicked: (Boolean) -> Unit,
    onScreenClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isSelected && screen == BottomBarScreen.RESERVATION) {
        onCenterTabClicked(true)
    }
    Column(
        modifier =
            modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        onScreenClicked()
                        onCenterTabClicked(false)
                    })
                },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(screen.iconLabelSpacing),
    ) {
        if (screen != BottomBarScreen.RESERVATION) {
            Icon(
                painter = painterResource(id = screen.unSelectedIcon),
                contentDescription = stringResource(screen.title),
                tint = if (isSelected) selectedColor else unselectedColor,
                modifier = Modifier.size(iconSize),
            )
        } else {
            Spacer(modifier = Modifier.size(iconSize))
        }
        Text(
            text = stringResource(screen.title),
            color = if (isSelected) selectedColor else unselectedColor,
            fontSize = labelSize,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
        )
    }
}

@Composable
private fun FloatingActionIconButton(
    state: NavigationBarState,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    route: String,
    size: Dp,
    iconId: Int,
    modifier: Modifier = Modifier,
    yOffset: Dp = (-57).dp,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
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
            tint = if (isSelected) selectedColor else unselectedColor,
        )
    }
}
