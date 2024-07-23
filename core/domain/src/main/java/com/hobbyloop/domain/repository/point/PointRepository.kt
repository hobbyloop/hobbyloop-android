package com.hobbyloop.domain.repository.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.point.PointTotalHistory

interface PointRepository {
    suspend fun getPointTotalHistory(): CustomResult<PointTotalHistory, DataError.Network>
}