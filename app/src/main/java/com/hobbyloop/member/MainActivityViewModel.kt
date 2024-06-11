package com.hobbyloop.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.usecase.user.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
    @Inject
    constructor(
        getUserDataUseCase: GetUserDataUseCase,
    ) : ViewModel() {
        val uiState =
            getUserDataUseCase().map {
                MainActivityUiState.Success(it)
            }.stateIn(
                scope = viewModelScope,
                initialValue = MainActivityUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )
    }