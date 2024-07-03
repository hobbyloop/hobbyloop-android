package com.hobbyloop.feature.reservation.reservation_detail

import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

private const val CLASS_ID_ARG = "classId"
internal const val RESERVATION_DETAIL_ROUTE = "reservation--detail/{$CLASS_ID_ARG}"

internal data class ReservationDetailArgs(val classId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        classId = checkNotNull(savedStateHandle[CLASS_ID_ARG])
    )
}

fun NavController.navigateToReservationDetail(classId: String) {
    navigate(RESERVATION_DETAIL_ROUTE.replace("{$CLASS_ID_ARG}", classId))
}

internal fun NavGraphBuilder.reservationDetailScreen(
    onCloseClick: () -> Unit,
    navigateToReservationCompletion: (classId: String) -> Unit,
) {
    dialog(
        route = RESERVATION_DETAIL_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        ReservationDetailScreen(
            onCloseClick = onCloseClick,
            navigateToReservationCompletion = navigateToReservationCompletion
        )
    }
}
