package com.hobbyloop.feature.mypage.mypoint.model

import com.hobbyloop.domain.entity.point.ExtinctionPoint
import com.hobbyloop.domain.entity.point.History
import com.hobbyloop.domain.entity.point.Point
import java.text.SimpleDateFormat
import java.util.Locale

data class ExtinctionPointUiModel(
    val point: String = "",
    val date: String = ""
)

data class HistoryUiModel(
    val point: String,
    val type: Int,
    val date: String,
    val totalPoint: String
)

data class PointUiModel(
    val point: String = "",
    val extinctionPoint: ExtinctionPointUiModel = ExtinctionPointUiModel(),
    val history: List<HistoryUiModel> = listOf()
)

fun ExtinctionPoint.toUiModel(): ExtinctionPointUiModel {
    return ExtinctionPointUiModel(
        point = this.point.toCommaSeparatedString(),
        date = this.date.toFormattedDate()
    )
}

fun History.toUiModel(): HistoryUiModel {
    return HistoryUiModel(
        point = this.point.toCommaSeparatedString(),
        type = this.type,
        date = this.date.toMonthDayFormat(),
        totalPoint = this.totalPoint.toCommaSeparatedString()
    )
}

fun Point.toUiModel(): PointUiModel {
    return PointUiModel(
        point = this.point.toCommaSeparatedString(),
        extinctionPoint = this.extinctionPoint.toUiModel(),
        history = this.history.map { it.toUiModel() }
    )
}

fun String.toFormattedDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy. MM. dd", Locale.getDefault())
    return try {
        val date = inputFormat.parse(this)
        if (date != null) {
            outputFormat.format(date)
        } else {
            this
        }
    } catch (e: Exception) {
        this
    }
}

fun String.toMonthDayFormat(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MM.dd", Locale.getDefault())
    return try {
        val date = inputFormat.parse(this)
        if (date != null) {
            outputFormat.format(date)
        } else {
            this
        }
    } catch (e: Exception) {
        this
    }
}

fun Int.toCommaSeparatedString(): String {
    return "%,d".format(this)
}