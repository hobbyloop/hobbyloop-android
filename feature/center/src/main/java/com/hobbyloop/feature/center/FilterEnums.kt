package com.hobbyloop.feature.center

import androidx.compose.ui.graphics.Color
import com.hobbyloop.ui.R.drawable
import theme.HobbyLoopColor


enum class SortingOption(val displayName: String) {
    RATING("별점순"),
    LATEST("최신순"),
    SALES("판매순"),
    REVIEW("리뷰순")
}

enum class ScoreOption(val displayName: String) {
    ALL("전체"),
    ABOVE_4_5("4.5점 이상"),
    ABOVE_4_0("4점 이상"),
    ABOVE_3_5("3.5점 이상")
}

enum class FilterType {
    RATING, LOCATION, REFUNDABLE, STAR
}

enum class FilterButtonData(
    val filterType: FilterType,
    val leftIconResId: Int? = null,
    val rightIconResId: Int? = null,
    val backgroundColor: Color
) {
    RATING(
        FilterType.RATING,
        leftIconResId = drawable.ic_updown,
        backgroundColor = HobbyLoopColor.White
    ),
    LOCATION(
        FilterType.LOCATION,
        rightIconResId = drawable.ic_down,
        backgroundColor = HobbyLoopColor.Gray20
    ),
    REFUNDABLE(FilterType.REFUNDABLE, backgroundColor = HobbyLoopColor.Gray20),
    STAR(
        FilterType.STAR,
        rightIconResId = drawable.ic_down,
        backgroundColor = HobbyLoopColor.Gray20
    )
}
