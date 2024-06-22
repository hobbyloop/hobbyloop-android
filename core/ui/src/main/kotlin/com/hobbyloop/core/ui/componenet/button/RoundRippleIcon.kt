package com.hobbyloop.core.ui.componenet.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.hobbyloop.ui.R

@Composable
fun RoundRippleIcon(
    @DrawableRes iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    overRipple: Boolean = false,
    rippleColor: Color = Color.Black,
) {
    val iconSize = getIconSize(iconResId)

    Box(
        modifier = Modifier
            .size(if (overRipple) iconSize * 2 else iconSize)
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    radius = iconSize,
                    color = rippleColor
                ),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            tint = Color.Unspecified
        )
    }
}

@Preview
@Composable
fun PreviewRoundRippleIcon() {
    Surface {
        RoundRippleIcon(
            iconResId = R.drawable.ic_back,
            contentDescription = "검색 아이콘",
            onClick = { },
            overRipple = true,
            rippleColor = Color.Black
        )
    }
}

@Composable
fun getIconSize(@DrawableRes iconResId: Int): Dp {
    val vector = ImageVector.vectorResource(id = iconResId)
    return vector.defaultWidth
}
