package model.point

import com.hobbyloop.domain.entity.point.ExtinctionPoint
import com.hobbyloop.domain.entity.point.History
import com.hobbyloop.domain.entity.point.Point

data class PointResponse(
    val point: Int,
    val extinctionPointResponse: ExtinctionPointResponse,
    val history: List<HistoryResponse>
)

data class ExtinctionPointResponse(
    val point: Int,
    val date: String
)

data class HistoryResponse(
    val point: Int,
    val type: Int,
    val date: String,
    val totalPoint: Int
)

fun ExtinctionPointResponse.toEntity(): ExtinctionPoint {
    return ExtinctionPoint(
        point = this.point,
        date = this.date
    )
}

fun HistoryResponse.toEntity(): History {
    return History(
        point = this.point,
        type = this.type,
        date = this.date,
        totalPoint = this.totalPoint
    )
}

fun PointResponse.toEntity(): Point {
    return Point(
        point = this.point,
        extinctionPoint = this.extinctionPointResponse.toEntity(),
        history = this.history.map { it.toEntity() }
    )
}

