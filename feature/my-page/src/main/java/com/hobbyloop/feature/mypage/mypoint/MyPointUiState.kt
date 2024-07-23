package com.hobbyloop.feature.mypage.mypoint

import com.hobbyloop.feature.mypage.mypoint.model.PointUiModel

data class MyPointUiState(
    val isLoading: Boolean = false,
    val point: PointUiModel = PointUiModel()
)