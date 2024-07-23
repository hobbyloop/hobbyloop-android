package com.hobbyloop.feature.mypage

data class MyPageUiState(
    val isLoading: Boolean = false,
    val userInfo: UserInfoUiModel? = null,
    val error: String? = null
)

sealed class MyPageSideEffect {
    data class ShowError(val message: String) : MyPageSideEffect()
}
