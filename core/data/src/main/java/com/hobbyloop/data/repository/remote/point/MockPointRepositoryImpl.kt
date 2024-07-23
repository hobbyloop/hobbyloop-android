package com.hobbyloop.data.repository.remote.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.point.PointTotalHistory
import com.hobbyloop.domain.repository.point.PointRepository
import datasource.point.PointDataSource
import model.point.toDomain
import javax.inject.Inject

class MockPointRepositoryImpl @Inject constructor(
    private val dataSource: PointDataSource
) : PointRepository {

    override suspend fun getPointTotalHistory(): CustomResult<PointTotalHistory, DataError.Network> {
        return when (val result = dataSource.getPointTotalHistory()) {
            is CustomResult.Success -> CustomResult.Success(result.data.toDomain())
            is CustomResult.Error -> CustomResult.Error(result.error)
        }
    }
}