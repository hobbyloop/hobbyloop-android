package com.hobbyloop.feature.reservation.reservation_completion

import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

private const val CLASS_ID_ARG = "classId"
internal const val RESERVATION_COMPLETION_ROUTE = "reservation-completion/{$CLASS_ID_ARG}"

internal data class ReservationClassCompletionArgs(val classId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        classId = checkNotNull(savedStateHandle[CLASS_ID_ARG])
    )
}

fun NavController.navigateToReservationCompletion(classId: String) {
    navigate(RESERVATION_COMPLETION_ROUTE.replace("{$CLASS_ID_ARG}", classId))
}

internal fun NavGraphBuilder.reservationCompletionScreen(
    navigateToHomeScreen: () -> Unit,
) {
    dialog(
        route = RESERVATION_COMPLETION_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        ReservationCompletionScreen(
            navigateToHomeScreen = navigateToHomeScreen
        )
    }
}
