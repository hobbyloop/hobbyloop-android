package com.hobbyloop.domain.entity.calendar

import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor

data class DateInfo(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayOfWeek: String,
    val classInfoList: List<Pair<Instructor, List<ClassInfo>>>? = null
)
