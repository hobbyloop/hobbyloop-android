package com.hobbyloop.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.hobbyloop.feature.home.model.AdUiModel
import com.hobbyloop.member.ui.theme.HobbyLoopTypo
import theme.HobbyLoopColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerAdvertise(
    modifier: Modifier = Modifier,
    banners: List<AdUiModel> = listOf(),
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(98.dp)
            .padding(horizontal = 16.dp)
    ) {
        HorizontalPager(
            count = banners.size,
            state = pagerState,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) { page ->
            BannerItem(banner = banners[page])
        }

        // Page number indicator
        Box(
            modifier = Modifier
                .offset(x = (-10).dp, y = (-10).dp)
                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(999.dp))
                .align(Alignment.BottomEnd)
                .padding(horizontal = 8.dp, vertical = 4.dp)

        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${banners.size}",
                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray40)
            )
        }
    }
}

@Composable
fun BannerItem(banner: AdUiModel) {
    AsyncImage(
            model = banner.bannerImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    shape = RoundedCornerShape(14.dp)
                    clip = true
                }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewBannerAdvertise() {
    BannerAdvertise(
        banners = listOf(
            AdUiModel(
                id = 1,
                centerId = 101,
                campaignName = "Summer Sale",
                centerName = "Fitness Center",
                content = "Join now and get 50% off!",
                keyword = "sale",
                bannerImageUrl = "",
                adStart = "2024-06-01",
                adEnd = "2024-08-01",
                adRank = 1
            ),
            AdUiModel(
                id = 2,
                centerId = 102,
                campaignName = "Winter Sale",
                centerName = "Yoga Center",
                content = "Join now and get 40% off!",
                keyword = "discount",
                bannerImageUrl = "",
                adStart = "2024-12-01",
                adEnd = "2025-01-31",
                adRank = 2
            )
        )
    )
}
