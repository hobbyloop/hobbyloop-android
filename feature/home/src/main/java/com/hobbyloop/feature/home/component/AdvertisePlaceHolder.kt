package com.hobbyloop.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdvertisePlaceHolder(
    modifier: Modifier = Modifier,
    placeHolderLabel: String,
    placeholderHeight: Dp = 82.dp,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Gray,
    textStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = textColor
    )
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(placeholderHeight)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = placeHolderLabel,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAdvertisePlaceHolder() {
    AdvertisePlaceHolder(placeHolderLabel = "광고")
}