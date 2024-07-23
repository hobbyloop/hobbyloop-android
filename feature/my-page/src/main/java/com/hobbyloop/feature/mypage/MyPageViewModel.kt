package com.hobbyloop.feature.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.usecase.user.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ContainerHost<MyPageUiState, MyPageSideEffect>, ViewModel() {

    override val container = container<MyPageUiState, MyPageSideEffect>(MyPageUiState())

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() = intent {
        reduce { state.copy(isLoading = true) }
        delay(2000)
        Timber.tag("MyPageViewModel").d("UserInfo loading}")
        viewModelScope.launch {
            when (val result = getUserInfoUseCase()) {
                is CustomResult.Success -> reduce {
                    Timber.tag("MyPageViewModel").d("UserInfo loaded successfully: %s", result.data)
                    state.copy(isLoading = false, userInfo = result.data.toUiModel(), error = null)
                }

                is CustomResult.Error -> {
                    val errorText = result.error.name
                    postSideEffect(MyPageSideEffect.ShowError(errorText))
                    reduce {
                        state.copy(isLoading = false, error = errorText)
                    }
                }
            }
        }
    }
}