package com.hobbyloop.domain.usecase.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.point.PointTotalHistory
import com.hobbyloop.domain.repository.point.PointRepository
import javax.inject.Inject

class GetPointTotalHistoryUseCase @Inject constructor(
    private val repository: PointRepository
) {
    suspend operator fun invoke(): CustomResult<PointTotalHistory, DataError.Network> {
        return repository.getPointTotalHistory()
    }
}