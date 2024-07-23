package com.hobbyloop.feature.mypage.mycoupon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.usecase.coupon.GetCouponsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyCouponViewModel @Inject constructor(
    private val getCouponsUseCase: GetCouponsUseCase
) : ContainerHost<MyCouponUiState, MyCouponSideEffect>, ViewModel() {

    override val container = container<MyCouponUiState, MyCouponSideEffect>(MyCouponUiState())

    init {
        loadCoupons()
    }

    private fun loadCoupons() = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch {
            getCouponsUseCase().collect { result ->
                when (result) {
                    is CustomResult.Success -> reduce {
                        state.copy(
                            isLoading = false,
                            coupons = result.data.map { it.toUiModel() },
                            error = null
                        )
                    }
                    is CustomResult.Error -> {
                        val errorText = result.error.name
                        postSideEffect(MyCouponSideEffect.ShowError(errorText))
                        reduce {
                            state.copy(isLoading = false, error = errorText)
                        }
                    }
                }
            }
        }
    }
}