package com.hobbyloopapp.feature.facility_card.card

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val CARD_ROUTE = "card"

internal fun NavGraphBuilder.cardScreen(
    onBackClick: () -> Unit,
    onCardDetailClick: (facilityId: String) -> Unit
) {
    composable(CARD_ROUTE) {
        val viewModel: CardViewModel = hiltViewModel()
        val facility = viewModel.facility

        if (facility != null) {
            CardScreen(
                facility = facility,
                onBackClick = onBackClick,
                onCardDetailClick = {
                    facility.id.let(onCardDetailClick)
                }
            )
        }
    }
}


