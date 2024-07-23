package com.hobbyloop.feature.mypage.myticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.usecase.ticket.GetTicketHistoryUseCase
import com.hobbyloop.feature.mypage.myticket.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyTicketViewModel @Inject constructor(
    private val getTicketHistoryUseCase: GetTicketHistoryUseCase
) : ContainerHost<MyTicketUiState, MyTicketSideEffect>, ViewModel() {

    override val container = container<MyTicketUiState, MyTicketSideEffect>(MyTicketUiState())

    init {
        loadTickets()
    }

    private fun loadTickets() = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch {
            getTicketHistoryUseCase().collect { result ->
                when (result) {
                    is CustomResult.Success -> reduce {
                        state.copy(
                            isLoading = false,
                            tickets = result.data.map { it.toUiModel() }
                        )
                    }
                    is CustomResult.Error -> reduce {
                        state.copy(isLoading = false, error = result.error.name)
                    }
                }
            }
        }
    }

    fun onTabSelected(tab: TicketTab) = intent {
        reduce { state.copy(selectedTab = tab) }
    }
}