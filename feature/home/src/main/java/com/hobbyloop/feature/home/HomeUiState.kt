package com.hobbyloop.feature.home

import com.hobbyloop.feature.home.model.BannerAdvertiseUiModel
import com.hobbyloop.feature.home.model.CategorySelectorItemUiModel
import com.hobbyloop.feature.home.model.HotTicketUiModel
import com.hobbyloop.feature.home.model.RecommendTicketUiModel
import com.hobbyloop.feature.home.model.TicketCategory
import com.hobbyloop.feature.home.model.UpcomingReservationUiModel

data class HomeUiState(
    val userName: String,
    val checkOnBoarding: Boolean = true,
    val upcomingReservation: UpcomingReservationUiModel = UpcomingReservationUiModel(),
    val categorySelectorItems: List<CategorySelectorItemUiModel> = listOf(),
    val bannerAdvertises: List<BannerAdvertiseUiModel> = listOf(),
    val hotTicketItems: List<HotTicketUiModel> = listOf(),
    val recommendCategory: TicketCategory = TicketCategory.PT,
    val recommendTicketItems: List<RecommendTicketUiModel> = listOf()
)