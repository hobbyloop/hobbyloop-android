package com.hobbyloop.feature.home

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.feature.home.component.AdvertisePlaceHolder
import com.hobbyloop.feature.home.component.BannerAdvertise
import com.hobbyloop.feature.home.component.CategorySelector
import com.hobbyloop.feature.home.component.HomeTitle
import com.hobbyloop.feature.home.component.HomeTopBar
import com.hobbyloop.feature.home.component.HotTicketSection
import com.hobbyloop.feature.home.component.RecommendTicketSection
import com.hobbyloop.feature.home.component.UpComingReservation

@Composable
internal fun HomeScreen(
    showOnBoarding: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    scrollState: ScrollState = rememberScrollState()
) {
    val state = viewModel.container.stateFlow.collectAsState()

    Scaffold(
        topBar = { HomeTopBar() },
    ) { padding ->
        Log.d("HomeScreen", "HomeScreen: $state")
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = Color(0xFFF9F9F9))
                .verticalScroll(scrollState)
        ) {
            HomeTitle(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 24.dp),
                text = "안녕하세요, ${state.value.userName}님",
            )

            UpComingReservation(
                modifier = Modifier.padding(horizontal = 16.dp),
                item = state.value.upcomingReservation
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(vertical = 24.dp)
            ) {
                BannerAdvertise(banners = state.value.ads)
                Spacer(modifier = Modifier.height(32.dp))
                CategorySelector(items = state.value.categorySelectorItems)
            }
            Spacer(modifier = Modifier.height(16.dp))

            AdvertisePlaceHolder(placeHolderLabel = "광고")

            Spacer(modifier = Modifier.height(16.dp))

            HotTicketSection(
                firstText = "이번주",
                highlightText = " HOT ",
                lastText = "이용권",
                items = state.value.hotTickets
            )

            Spacer(modifier = Modifier.height(16.dp))

            RecommendTicketSection(
                category = state.value.recommendCategory,
                items = state.value.recommendTicketItems
            )

            Spacer(modifier = Modifier.height(16.dp))

            AdvertisePlaceHolder(placeHolderLabel = "플랫폼 전용 이벤트")

            Spacer(modifier = Modifier.height(95.dp))

        }
    }
}

@Composable
@Preview
private fun PreviewHomeScreen() {
    HomeScreen(
        showOnBoarding = {},
    )
}

