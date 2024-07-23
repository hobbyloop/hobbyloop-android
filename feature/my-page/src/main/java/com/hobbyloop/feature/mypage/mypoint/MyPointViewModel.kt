package com.hobbyloop.feature.mypage.mypoint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.usecase.point.GetPointHistoryUseCase
import com.hobbyloop.feature.mypage.mypoint.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyPointViewModel @Inject constructor(
    private val getPointHistoryUseCase: GetPointHistoryUseCase
) : ContainerHost<MyPointUiState, Nothing>, ViewModel(){

    override val container = container<MyPointUiState, Nothing>(MyPointUiState())

    init {
        loadPointHistory()
    }

    private fun loadPointHistory() = intent {
        viewModelScope.launch {
            getPointHistoryUseCase().collect{ resource ->
                when (resource) {
                    is Resource.Success -> reduce {
                        state.copy(isLoading = false, point = resource.data!!.toUiModel())
                    }

                    is Resource.Error -> reduce {
                        state.copy(isLoading = false)
                    }
                }
            }
        }
    }
}