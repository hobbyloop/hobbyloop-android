package com.hobbyloop.domain.entity.point

import java.util.Date

data class PointDayHistory(
    val type: String,
    val amount: Int,
    val balance: Int,
    val description: String,
    val createdAt: Date // Date object for better manipulation
)

data class PointMonthHistory(
    val yearMonth: String,
    val pointHistories: List<PointDayHistory>
)

data class PointTotalHistory(
    val totalPoints: Int,
    val pointHistories: List<PointMonthHistory>
)
