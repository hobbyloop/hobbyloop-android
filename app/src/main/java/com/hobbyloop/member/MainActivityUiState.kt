package com.hobbyloop.member

import com.hobbyloop.domain.entity.user.UserData

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState

    data class Success(val userData: UserData) : MainActivityUiState
}