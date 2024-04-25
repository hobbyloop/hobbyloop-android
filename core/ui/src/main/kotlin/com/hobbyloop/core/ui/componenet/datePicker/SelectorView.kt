package com.hobbyloop.core.ui.componenet.datePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SelectorView(modifier: Modifier = Modifier, darkModeEnabled: Boolean, offset: Int) {
    Column(
        modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(offset.toFloat())
                .fillMaxWidth()
                .background(Color.Transparent.copy(alpha = 0.01f))
        )
        Box(
            modifier = Modifier
                .weight(offset.toFloat())
                .fillMaxWidth()
                .background(Color.Transparent.copy(alpha = 0.01f))
        )
    }
}