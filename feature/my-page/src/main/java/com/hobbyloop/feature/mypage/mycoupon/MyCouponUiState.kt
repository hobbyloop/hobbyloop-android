package com.hobbyloop.feature.mypage.mycoupon

data class MyCouponUiState(
    val isLoading: Boolean = false,
    val coupons: List<CouponUiModel> = emptyList(),
    val error: String? = null
)

sealed class MyCouponSideEffect {
    data class ShowError(val message: String) : MyCouponSideEffect()
}