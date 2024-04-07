package com.hobbyloopapp.feature.facility_card.card_detail

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val FACILITY_ID_ARG = "facilityId"
private const val CARD_DETAIL_ROUTE = "cardDetail/{$FACILITY_ID_ARG}"

internal data class CardDetailArgs(val facilityId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        facilityId = checkNotNull(savedStateHandle[FACILITY_ID_ARG])
    )
}

internal fun NavController.navigateToCardDetail(facilityId: String) {
    navigate(CARD_DETAIL_ROUTE.replace("{$FACILITY_ID_ARG}", facilityId))
}

internal fun NavGraphBuilder.cardDetailScreen(onBackClick: () -> Unit) {
    composable(route = CARD_DETAIL_ROUTE) {
        val viewModel: CardDetailViewModel = hiltViewModel()
        val facility = viewModel.facility

        if (facility != null) {
            CardDetailScreen(
                facility = facility,
                onBackClick = onBackClick
            )
        }
    }
}
