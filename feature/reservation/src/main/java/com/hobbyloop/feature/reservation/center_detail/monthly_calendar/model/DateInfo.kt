package com.hobbyloop.feature.reservation.center_detail.monthly_calendar.model

import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo
import com.hobbyloop.feature.reservation.center_detail.model.Instructor

data class DateInfo(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayOfWeek: String,
    val classInfoList: List<Pair<Instructor, List<ClassInfo>>>? = null
)
