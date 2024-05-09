package com.hobbyloop.feature.home.model

import androidx.annotation.DrawableRes

data class CategorySelectorItemUiModel(
    val category: TicketCategory,
    @DrawableRes val icon: Int
)