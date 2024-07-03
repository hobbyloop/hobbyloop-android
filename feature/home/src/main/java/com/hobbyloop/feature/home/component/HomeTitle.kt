package com.hobbyloop.feature.home.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun HomeTitle(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = HobbyLoopTypo.head22.copy(color = HobbyLoopColor.Gray100)
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeTitle() {
    HomeTitle(text = "안녕하세요, 김하비님")
}
