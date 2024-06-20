package com.hobbyloop.domain.entity.class_info

data class ClassInfo(
    val classId: Int,
    val dateTime: String, // "YYYY-MM-DD HH:MM - HH:MM" 포맷으로 날짜와 시간을 포함
    val title: String,
    val difficulty: String,
    val currentReservationCount: Int,
    val fullReservationCount: Int
)
