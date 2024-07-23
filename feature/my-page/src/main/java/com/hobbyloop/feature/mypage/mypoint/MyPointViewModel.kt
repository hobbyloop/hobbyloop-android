package com.hobbyloop.feature.mypage.mypoint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.usecase.point.GetPointTotalHistoryUseCase
import com.hobbyloop.feature.mypage.MyPageSideEffect
import com.hobbyloop.feature.mypage.mypoint.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyPointViewModel @Inject constructor(
    private val getPointTotalHistoryUseCase: GetPointTotalHistoryUseCase
) : ContainerHost<MyPointUiState, Nothing>, ViewModel() {

    override val container = container<MyPointUiState, Nothing>(MyPointUiState())

    init {
        loadPointTotalHistory()
    }

    private fun loadPointTotalHistory() = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch {
            when(val result = getPointTotalHistoryUseCase()) {
                is CustomResult.Success -> reduce {
                    state.copy(isLoading = false, point = result.data.toUiModel())
                }
                is CustomResult.Error -> {

                }
            }
        }
    }


}