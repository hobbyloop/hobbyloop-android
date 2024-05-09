package com.hobbyloop.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.home.model.BannerAdvertiseUiModel
import com.hobbyloop.member.ui.theme.HobbyLoopTypo
import theme.HobbyLoopColor

@Composable
fun BannerAdvertise(
    modifier: Modifier = Modifier,
    banners: List<BannerAdvertiseUiModel> = listOf(),
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(98.dp)
            .padding(horizontal = 16.dp)
            .background(Color.Gray, RoundedCornerShape(14.dp))
            .padding(start = 20.dp, top = 18.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = banners.size.toString(),
            style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray40)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBannerAdvertise() {
    BannerAdvertise()
}