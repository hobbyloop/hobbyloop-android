package com.hobbyloop.feature.home

import com.hobbyloop.feature.home.model.AdUiModel
import com.hobbyloop.feature.home.model.BannerAdvertiseUiModel
import com.hobbyloop.feature.home.model.CategorySelectorItemUiModel
import com.hobbyloop.feature.home.model.HotTicketUiModel
import com.hobbyloop.feature.home.model.RecommendTicketUiModel
import com.hobbyloop.feature.home.model.TicketCategory
import com.hobbyloop.feature.home.model.UpcomingReservationUiModel

data class HomeMockUiState(
    val isLoading: Boolean = false,
    val ads: List<AdUiModel> = listOf(),
    val error: String = "",
    val userName: String = "",
    val checkOnBoarding: Boolean = true,
    val upcomingReservation: UpcomingReservationUiModel = UpcomingReservationUiModel(),
    val categorySelectorItems: List<CategorySelectorItemUiModel> = listOf(),
    val bannerAdvertises: List<BannerAdvertiseUiModel> = listOf(),
    val hotTickets: List<HotTicketUiModel> = listOf(),
    val recommendCategory: TicketCategory = TicketCategory.PT,
    val recommendTicketItems: List<RecommendTicketUiModel> = listOf()
)