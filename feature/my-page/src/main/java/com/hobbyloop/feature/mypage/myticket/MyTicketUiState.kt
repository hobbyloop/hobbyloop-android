package com.hobbyloop.feature.mypage.myticket

data class MyTicketUiState(
    val isLoading: Boolean = false,
    val tickets: List<TicketHistoryUiModel> = emptyList(),
    val history: List<MonthHistoryUiModel> = emptyList(),
    val selectedTab: TicketTab = TicketTab.USAGE,
    val error: String? = null
)

sealed class MyTicketSideEffect {
    data class ShowError(val message: String) : MyTicketSideEffect()
}