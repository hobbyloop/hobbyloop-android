package com.hobbyloop.core.ui.componenet.top_bar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.button.RoundRippleIcon
import theme.HobbyLoopTypo
import com.hobbyloop.ui.R

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    appBarStyle: AppBarStyle
) {
    when (appBarStyle) {
        is AppBarStyle.NavigationIC -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                if (appBarStyle.navigationIcon.onClick != null) {
                    RoundRippleIcon(
                        overRipple = true,
                        rippleColor = Color.Black,
                        iconResId = appBarStyle.navigationIcon.iconResId,
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        onClick = appBarStyle.navigationIcon.onClick
                    )
                } else {
                    Icon(
                        painter = painterResource(id = appBarStyle.navigationIcon.iconResId),
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        tint = Color.Unspecified,
                    )
                }
            }
        }

        is AppBarStyle.Title -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = appBarStyle.title,
                    modifier = Modifier.align(Alignment.Center),
                    style = HobbyLoopTypo.head16
                )
            }
        }

        is AppBarStyle.ActionIC -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.CenterEnd
            ) {
                Row {
                    appBarStyle.actionIcons.forEachIndexed { index, actionIcon ->
                        actionIcon.onClick?.let {
                            RoundRippleIcon(
                                overRipple = false,
                                rippleColor = Color.Black,
                                iconResId = actionIcon.iconResId,
                                contentDescription = actionIcon.contentDescription,
                                onClick = it
                            )
                        }

                        if (index != appBarStyle.actionIcons.size - 1) {
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
            }
        }

        is AppBarStyle.NavigationICAndTitle -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                if (appBarStyle.navigationIcon.onClick != null) {
                    RoundRippleIcon(
                        overRipple = true,
                        rippleColor = Color.Black,
                        iconResId = appBarStyle.navigationIcon.iconResId,
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        onClick = appBarStyle.navigationIcon.onClick
                    )
                } else {
                    Icon(
                        painter = painterResource(id = appBarStyle.navigationIcon.iconResId),
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        tint = Color.Unspecified,
                    )
                }

                Text(
                    text = appBarStyle.title,
                    modifier = Modifier.align(Alignment.Center),
                    style = HobbyLoopTypo.head16
                )
            }
        }

        is AppBarStyle.NavigationICAndActionIC -> {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (appBarStyle.navigationIcon.onClick != null) {
                    RoundRippleIcon(
                        overRipple = true,
                        rippleColor = Color.Black,
                        iconResId = appBarStyle.navigationIcon.iconResId,
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        onClick = appBarStyle.navigationIcon.onClick
                    )
                } else {
                    Icon(
                        painter = painterResource(id = appBarStyle.navigationIcon.iconResId),
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        tint = Color.Unspecified,
                    )
                }

                Row {
                    appBarStyle.actionIcons.forEachIndexed { index, actionIcon ->
                        actionIcon.onClick?.let {
                            RoundRippleIcon(
                                overRipple = false,
                                rippleColor = Color.Black,
                                iconResId = actionIcon.iconResId,
                                contentDescription = actionIcon.contentDescription,
                                onClick = it
                            )
                        }

                        if (index != appBarStyle.actionIcons.size - 1) {
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
            }
        }

        is AppBarStyle.NavigationICTitleAndActionIC -> {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (appBarStyle.navigationIcon.onClick != null) {
                    RoundRippleIcon(
                        overRipple = true,
                        rippleColor = Color.Black,
                        iconResId = appBarStyle.navigationIcon.iconResId,
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        onClick = appBarStyle.navigationIcon.onClick
                    )
                } else {
                    Icon(
                        painter = painterResource(id = appBarStyle.navigationIcon.iconResId),
                        contentDescription = appBarStyle.navigationIcon.contentDescription,
                        tint = Color.Unspecified,
                    )
                }

                Text(
                    text = appBarStyle.title,
                    style = HobbyLoopTypo.head16
                )

                Row {
                    appBarStyle.actionIcons.forEachIndexed { index, actionIcon ->
                        actionIcon.onClick?.let {
                            RoundRippleIcon(
                                overRipple = false,
                                rippleColor = Color.Black,
                                iconResId = actionIcon.iconResId,
                                contentDescription = actionIcon.contentDescription,
                                onClick = it
                            )
                        }

                        if (index != appBarStyle.actionIcons.size - 1) {
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavigationAppBar() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp),
            appBarStyle = AppBarStyle.NavigationIC(
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.ic_back,
                    contentDescription = "뒤로가기",
                    onClick = {}
                )
            ),
        )
    }
}

@Preview
@Composable
fun PreviewTitleAppBar() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(horizontal = 16.dp),
            appBarStyle = AppBarStyle.Title(
                title = "예약 완료"
            ),
        )
    }
}

@Preview
@Composable
fun PreviewActionsAppBar() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(horizontal = 16.dp),
            appBarStyle = AppBarStyle.ActionIC(
                listOf(
                    ActionIcon(
                        iconResId = R.drawable.ic_search,
                        contentDescription = "검색"
                    ) {},
                    ActionIcon(
                        iconResId = R.drawable.ic_notice,
                        contentDescription = "알림 확인"
                    ) {}
                )
            ),
        )
    }
}

@Preview
@Composable
fun PreviewNavigationTitleAppBar() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp),
            appBarStyle = AppBarStyle.NavigationICAndTitle(
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.ic_back,
                    contentDescription = "뒤로가기",
                    onClick = {}
                ),
                title = "수업 예약"
            ),
        )
    }
}

@Preview
@Composable
fun PreviewNavigationTitleAppBar2() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(horizontal = 16.dp),
            appBarStyle = AppBarStyle.NavigationICAndTitle(
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.logo_small,
                    contentDescription = "뒤로가기",
                ),
                title = "발란스 스튜디오"
            ),
        )
    }
}

@Preview
@Composable
fun PreviewNavigationActionAppBar() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(horizontal = 16.dp),
            appBarStyle = AppBarStyle.NavigationICAndActionIC(
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.logo_small,
                    contentDescription = "로고 아이콘",
                ),
                actionIcons = listOf(
                    ActionIcon(
                        iconResId = R.drawable.ic_search,
                        contentDescription = "검색"
                    ) {},
                    ActionIcon(
                        iconResId = R.drawable.ic_notice,
                        contentDescription = "알림 확인"
                    ) {}
                )
            ),
        )
    }
}

@Preview
@Composable
fun PreviewNavigationActionAppBar2() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(end = 16.dp),
            appBarStyle = AppBarStyle.NavigationICAndActionIC(
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.ic_back,
                    contentDescription = "뒤로가기",
                    onClick = {}
                ),
                actionIcons = listOf(
                    ActionIcon(
                        iconResId = R.drawable.ic_search,
                        contentDescription = "검색"
                    ) {},
                    ActionIcon(
                        iconResId = R.drawable.ic_notice,
                        contentDescription = "알림 확인"
                    ) {}
                )
            ),
        )
    }
}

@Preview
@Composable
fun PreviewNavigationTitleActionAppBar() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(horizontal = 16.dp),
            appBarStyle = AppBarStyle.NavigationICTitleAndActionIC(
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.logo_small,
                    contentDescription = "뒤로가기",
                ),
                title = "발란스 스튜디오",
                actionIcons = listOf(
                    ActionIcon(
                        iconResId = R.drawable.ic_search,
                        contentDescription = "검색"
                    ) {},
                    ActionIcon(
                        iconResId = R.drawable.ic_notice,
                        contentDescription = "알림 확인"
                    ) {}
                )
            ),
        )
    }
}

@Preview
@Composable
fun PreviewNavigationTitleActionAppBar2() {
    Surface {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(end = 16.dp),
            appBarStyle = AppBarStyle.NavigationICTitleAndActionIC(
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.ic_back,
                    contentDescription = "뒤로가기",
                    onClick = {}
                ),
                title = "수업 예약",
                actionIcons = listOf(
                    ActionIcon(
                        iconResId = R.drawable.ic_search,
                        contentDescription = "검색"
                    ) {},
                    ActionIcon(
                        iconResId = R.drawable.ic_notice,
                        contentDescription = "알림 확인"
                    ) {}
                )
            ),
        )
    }
}

data class ActionIcon(
    @DrawableRes val iconResId: Int,
    val contentDescription: String,
    val onClick: (() -> Unit)? = null
)

sealed class AppBarStyle {
    data class NavigationIC(val navigationIcon: ActionIcon) : AppBarStyle()
    data class Title(val title: String) : AppBarStyle()
    data class ActionIC(val actionIcons: List<ActionIcon>) : AppBarStyle()
    data class NavigationICAndTitle(
        val navigationIcon: ActionIcon,
        val title: String
    ) : AppBarStyle()

    data class NavigationICAndActionIC(
        val navigationIcon: ActionIcon,
        val actionIcons: List<ActionIcon>
    ) : AppBarStyle()

    data class NavigationICTitleAndActionIC(
        val navigationIcon: ActionIcon,
        val title: String,
        val actionIcons: List<ActionIcon>
    ) : AppBarStyle()
}
