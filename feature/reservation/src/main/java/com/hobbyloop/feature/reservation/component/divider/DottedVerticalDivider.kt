package com.hobbyloop.feature.reservation.component.divider

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect

@Composable
fun DottedVerticalDivider(
    modifier: Modifier,
    color: Color,
    dashLength: Float = 20f, // 점선의 길이
    spaceLength: Float = 10f, // 점선 사이의 공간
    strokeWidth: Float = 2f // 점선의 두께
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            color = color, // 절취선의 색상
            start = Offset(x = canvasWidth / 2, y = 0f),
            end = Offset(x = canvasWidth / 2, y = canvasHeight),
            strokeWidth = strokeWidth,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, spaceLength), 0f)
        )
    }
}

