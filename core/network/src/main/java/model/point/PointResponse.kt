package model.point

import com.hobbyloop.domain.entity.point.PointDayHistory
import com.hobbyloop.domain.entity.point.PointMonthHistory
import com.hobbyloop.domain.entity.point.PointTotalHistory
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Serializable
data class PointTotalHistoryResponse(
    val totalPoints: Int,
    val pointHistories: List<PointMonthHistoryResponse>
)

@Serializable
data class PointMonthHistoryResponse(
    val yearMonth: String,
    val pointHistories: List<PointDayHistoryResponse>
)

@Serializable
data class PointDayHistoryResponse(
    val type: String,
    val amount: Int,
    val balance: Int,
    val description: String,
    val createdAt: String
)

fun PointTotalHistoryResponse.toDomain(): PointTotalHistory {
    return PointTotalHistory(
        totalPoints = this.totalPoints,
        pointHistories = this.pointHistories.map { it.toDomain() }
    )
}

fun PointMonthHistoryResponse.toDomain(): PointMonthHistory {
    return PointMonthHistory(
        yearMonth = this.yearMonth,
        pointHistories = this.pointHistories.map { it.toDomain() }
    )
}

fun PointDayHistoryResponse.toDomain(): PointDayHistory {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date = dateFormat.parse(this.createdAt) ?: Date()
    return PointDayHistory(
        type = this.type,
        amount = this.amount,
        balance = this.balance,
        description = this.description,
        createdAt = date
    )
}