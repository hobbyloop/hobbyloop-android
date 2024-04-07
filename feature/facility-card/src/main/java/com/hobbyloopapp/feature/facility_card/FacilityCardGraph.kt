package com.hobbyloopapp.feature.facility_card

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK
import com.hobbyloopapp.feature.facility_card.card.CARD_ROUTE
import com.hobbyloopapp.feature.facility_card.card.cardScreen
import com.hobbyloopapp.feature.facility_card.card_detail.cardDetailScreen
import com.hobbyloopapp.feature.facility_card.card_detail.navigateToCardDetail

private const val FACILITY_ID_ARG = "facilityId"
const val FACILITY_CARD_GRAPH_ROUTE = "facility-card-graph/{$FACILITY_ID_ARG}"
private const val FACILITY_CARD_DEEPLINK = "$ROOT_DEEPLINK/facility-card/{$FACILITY_ID_ARG}"

internal data class FacilityCardArgs(val facilityId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        facilityId = checkNotNull(savedStateHandle[FACILITY_ID_ARG])
    )
}

fun NavGraphBuilder.facilityCardGraph(navController: NavController) {
    navigation(
        startDestination = CARD_ROUTE,
        route = FACILITY_CARD_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = FACILITY_CARD_DEEPLINK }
        )
    ) {
        cardScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onCardDetailClick = { facilityId ->
                navController.navigateToCardDetail(facilityId)
            }
        )

        /**
         * cardScreen에서 클릭하여 한 뎁스 더 들어가는 화면
         * 추후 적절한 네이밍으로 수정 필요
         */
        cardDetailScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}

fun NavController.navigateToProductCardGraph(facilityId: String) {
    navigate(FACILITY_CARD_GRAPH_ROUTE.replace("{$FACILITY_ID_ARG}", facilityId))
}
