package com.hobbyloop.domain.entity.point

data class ExtinctionPoint(
    val point: Int,
    val date: String
)

data class History(
    val point: Int,
    val type: Int,
    val date: String,
    val totalPoint: Int
)

data class Point(
    val point: Int,
    val extinctionPoint: ExtinctionPoint,
    val history: List<History>
)