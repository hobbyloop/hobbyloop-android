package com.hobbyloop.feature.reservation.component.box

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.reservation.Gray20

@Composable
fun OutlinedGradientBox(
    borderWidth: Dp,
    gradientColors: List<Color>,
    borderShape: Shape,
    modifier: Modifier = Modifier,
    isSelectedColorAvailable: Boolean = false,
    isSelected: Boolean = false,
    unSelectedColor: Color = Gray20,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    val borderModifier = if (isSelectedColorAvailable) {
        Modifier.border(
            width = borderWidth,
            brush = if (isSelected) {
                Brush.linearGradient(
                    colors = gradientColors
                )
            } else {
                SolidColor(Color.White)
            },
            shape = borderShape
        )
    } else {
        Modifier.border(
            width = borderWidth,
            brush = Brush.linearGradient(
                colors = gradientColors
            ),
            shape = borderShape
        )
    }

    Box(
        modifier = modifier
            .then(borderModifier)
            .background(
                color = unSelectedColor,
                shape = borderShape
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewOutlinedGradientBoxForRoundedVer1() {
    Surface {
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            OutlinedGradientBox(
                modifier = Modifier
                    .width(358.dp)
                    .height(125.dp),
                borderWidth = 2.dp,
                gradientColors = listOf(
                    Color(0xFF9EB6FC),
                    Color(0xFFFFAAAA)
                ),
                borderShape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 10.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 40.dp
                ),
                onClick = { }
            )
        }
    }
}

@Preview
@Composable
fun PreviewOutlinedGradientBoxForRoundedVer2() {
    Surface {
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            OutlinedGradientBox(
                modifier = Modifier
                    .width(358.dp)
                    .height(125.dp),
                borderWidth = 2.dp,
                gradientColors = listOf(
                    Color(0xFF9EB6FC),
                    Color(0xFFFFAAAA)
                ),
                borderShape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 8.dp,
                    bottomEnd = 8.dp,
                    bottomStart = 8.dp
                ),
                onClick = { }
            )
        }
    }
}

@Preview
@Composable
fun PreviewOutlinedGradientBoxForSelectedAvailable() {
    Surface {
        var isSelected by remember {
            mutableStateOf(false)
        }

        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            OutlinedGradientBox(
                modifier = Modifier
                    .width(358.dp)
                    .height(125.dp),
                borderWidth = 2.dp,
                gradientColors = listOf(
                    Color(0xFF9EB6FC),
                    Color(0xFFFFAAAA)
                ),
                borderShape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 8.dp,
                    bottomEnd = 8.dp,
                    bottomStart = 8.dp
                ),
                isSelectedColorAvailable = true,
                isSelected = isSelected,
                unSelectedColor = Gray20,
                onClick = { isSelected = isSelected.not() }
            )
        }
    }
}
