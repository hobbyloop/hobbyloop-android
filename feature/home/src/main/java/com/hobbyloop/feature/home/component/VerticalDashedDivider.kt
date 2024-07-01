package com.hobbyloop.feature.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDashedDivider(
    modifier: Modifier = Modifier,
    dashWidth: Dp = 6.dp,
    dashHeight: Dp = 2.dp,
    gapWidth: Dp = 6.dp,
    color: Color = Color(0xFFD7D7D7),
) {
    Canvas(modifier = modifier) {
        val pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashWidth.toPx(), gapWidth.toPx()),
            phase = 0f
        )
        drawLine(
            color = color,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = 0f, y = size.height),
            pathEffect = pathEffect,
            strokeWidth = dashHeight.toPx()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewVerticalDashedDivider() {
    VerticalDashedDivider(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
    )
}