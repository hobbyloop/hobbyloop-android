package com.hobbyloop.feature.mypage.mypoint.model

import com.hobbyloop.domain.entity.point.PointDayHistory
import com.hobbyloop.domain.entity.point.PointMonthHistory
import com.hobbyloop.domain.entity.point.PointTotalHistory
import java.text.SimpleDateFormat
import java.util.Locale


data class PointHistoryUiModel(
    val type: String,
    val amount: Int,
    val balance: Int,
    val description: String,
    val createdAt: String // Readable date string
)

data class PointMonthHistoryUiModel(
    val yearMonth: String,
    val pointHistories: List<PointHistoryUiModel>
)

data class PointUiModel(
    val totalPoints: Int = 0,
    val history: List<PointMonthHistoryUiModel> = emptyList(),
    val extinctionPoint: ExtinctionPointUiModel = ExtinctionPointUiModel()
)

data class ExtinctionPointUiModel(
    val point: Int = 0,
    val date: String = ""
)


fun PointTotalHistory.toUiModel(): PointUiModel {
    return PointUiModel(
        totalPoints = this.totalPoints,
        history = this.pointHistories.map { it.toUiModel() },
        extinctionPoint = ExtinctionPointUiModel(
            point = this.pointHistories.firstOrNull()?.pointHistories?.firstOrNull()?.amount ?: 0,
            date = this.pointHistories.firstOrNull()?.pointHistories?.firstOrNull()?.createdAt.toString()
        )
    )
}

fun PointMonthHistory.toUiModel(): PointMonthHistoryUiModel {
    return PointMonthHistoryUiModel(
        yearMonth = this.yearMonth,
        pointHistories = this.pointHistories.map { it.toUiModel() }
    )
}

fun PointDayHistory.toUiModel(): PointHistoryUiModel {
    val readableDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val readableDate = readableDateFormat.format(this.createdAt)
    return PointHistoryUiModel(
        type = this.type,
        amount = this.amount,
        balance = this.balance,
        description = this.description,
        createdAt = readableDate
    )
}