package com.hobbyloop.feature.mypage.myticket

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

internal const val MY_TICKET_ROUTE = "my-ticket"

internal fun NavController.navigateToMyTicket() {
    navigate(MY_TICKET_ROUTE)
}

internal fun NavGraphBuilder.myTicketScreen(onCloseClick: () -> Unit) {
    dialog(
        route = MY_TICKET_ROUTE,
        dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MyTicketScreen(
            onCloseClick = onCloseClick,
            onSaveClick = {

            },
        )
    }
}
