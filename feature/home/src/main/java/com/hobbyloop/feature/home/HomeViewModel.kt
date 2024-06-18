package com.hobbyloop.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.usecase.ad.GetAdsUseCase
import com.hobbyloop.domain.usecase.ticket.GetHotTicketsUseCase
import com.hobbyloop.domain.usecase.ticket.GetRecommendTicketsUseCase
import com.hobbyloop.feature.home.model.CategorySelectorItemUiModel
import com.hobbyloop.feature.home.model.HotTicketUiModel
import com.hobbyloop.feature.home.model.RecommendTicketUiModel
import com.hobbyloop.feature.home.model.TicketCategory
import com.hobbyloop.feature.home.model.UpcomingReservationUiModel
import com.hobbyloop.feature.home.model.toUiModel
import com.hobbyloop.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAdsUseCase: GetAdsUseCase,
    private val getHotTicketsUseCase: GetHotTicketsUseCase,
    private val getRecommendTicketsUseCase: GetRecommendTicketsUseCase,
) : ContainerHost<HomeMockUiState, HomeMockSideEffect>, ViewModel() {

    override val container = container<HomeMockUiState, HomeMockSideEffect>(HomeMockUiState())

    init {
        loadAds()
    }

    private fun loadAds() = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch {
            getAdsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> reduce {
                        state.copy(isLoading = false, ads = resource.data!!.map { it.toUiModel() })
                    }

                    is Resource.Error -> reduce {
                        state.copy(isLoading = false, error = "")
                    }
                }
            }
            getHotTicketsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> reduce {
                        state.copy(isLoading = false, hotTickets = resource.data!!.map { it.toUiModel() })
                    }

                    is Resource.Error -> reduce {
                        state.copy(isLoading = false, error = "")
                    }
                }
            }
            getRecommendTicketsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> reduce {
                        state.copy(isLoading = false, recommendTicketItems = resource.data!!.map { it.toUiModel() })
                    }

                    is Resource.Error -> reduce {
                        state.copy(isLoading = false, error = "")
                    }
                }

            }

        }
        reduce {
            state.copy(
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
                recommendCategory = TicketCategory.PT,
            )
        }
    }
}








