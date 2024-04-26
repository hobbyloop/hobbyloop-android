package com.hobbyloop.core.ui.componenet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLine(modifier : Modifier = Modifier, color : Color = Color(0xFFD7D7D7), thickness : Dp = 1.dp) {
    HorizontalDivider(
        color = color,
        thickness = thickness,
        modifier = modifier.fillMaxWidth()
    )
}