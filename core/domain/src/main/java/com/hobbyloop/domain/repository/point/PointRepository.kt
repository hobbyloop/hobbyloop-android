package com.hobbyloop.domain.repository.point

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.point.Point
import kotlinx.coroutines.flow.Flow

interface PointRepository {
    fun getPointHistory(): Flow<Resource<Point>>
}