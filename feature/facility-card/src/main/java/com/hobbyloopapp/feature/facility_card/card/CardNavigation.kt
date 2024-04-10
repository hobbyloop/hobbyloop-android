package com.hobbyloopapp.feature.facility_card.card

import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val CARD_ROUTE = "card"

internal fun NavGraphBuilder.cardScreen(
    onBackClick: () -> Unit,
    onCardDetailClick: (facilityId: String) -> Unit
) {
    dialog(
        CARD_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
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


