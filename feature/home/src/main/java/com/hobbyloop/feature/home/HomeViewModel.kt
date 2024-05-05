package com.hobbyloop.feature.home

import androidx.lifecycle.ViewModel
import com.hobbyloop.feature.home.model.BannerAdvertiseUiModel
import com.hobbyloop.feature.home.model.CategorySelectorItemUiModel
import com.hobbyloop.feature.home.model.HotTicketUiModel
import com.hobbyloop.feature.home.model.RecommendTicketUiModel
import com.hobbyloop.feature.home.model.TicketCategory
import com.hobbyloop.feature.home.model.UpcomingReservationUiModel
import com.hobbyloop.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            userName = "김하비",
            upcomingReservation = UpcomingReservationUiModel(
                className = "6:1 체형교정 필라테스",
                centerName = "필라피티 스튜디오",
                teacherName = "이민주 강사님",
                classPeriod = "2023. 5. 12 금 09:00 - 09:50",
                qr = "",
                centerLogoUrl = ""
            ),
            categorySelectorItems = listOf(
                CategorySelectorItemUiModel(category = TicketCategory.PT, icon = R.drawable.pictogram_pt),
                CategorySelectorItemUiModel(category = TicketCategory.CLIMB, icon = R.drawable.pictogram_climb),
                CategorySelectorItemUiModel(category = TicketCategory.CROSS, icon = R.drawable.pictogram_cross),
                CategorySelectorItemUiModel(category = TicketCategory.DANCE, icon = R.drawable.pictogram_dance),
                CategorySelectorItemUiModel(category = TicketCategory.GOLF, icon = R.drawable.pictogram_golf),
                CategorySelectorItemUiModel(category = TicketCategory.PILATES, icon = R.drawable.pictogram_pila),
                CategorySelectorItemUiModel(category = TicketCategory.TENNIS, icon = R.drawable.pictogram_tennis),
                CategorySelectorItemUiModel(category = TicketCategory.YOGA, icon = R.drawable.pictogram_yoga),
            ),
            hotTicketItems = listOf(
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
            ),
            recommendCategory = TicketCategory.PT,
            recommendTicketItems = listOf(
                RecommendTicketUiModel(
                    isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    category = TicketCategory.PILATES,
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                RecommendTicketUiModel(
                    isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    category = TicketCategory.PILATES,
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                RecommendTicketUiModel(
                    isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    category = TicketCategory.PILATES,
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                )
            )

        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}








