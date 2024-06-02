package com.hobbyloop.domain.usecase.point

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ad.Ad
import com.hobbyloop.domain.entity.point.Point
import com.hobbyloop.domain.repository.point.PointRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPointHistoryUseCase @Inject constructor(
    private val repository: PointRepository
){
    operator fun invoke(): Flow<Resource<Point>> {
        return repository.getPointHistory()
    }
}