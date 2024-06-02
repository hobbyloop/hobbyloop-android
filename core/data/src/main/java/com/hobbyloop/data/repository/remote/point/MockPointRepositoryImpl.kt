package com.hobbyloop.data.repository.remote.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ad.Ad
import com.hobbyloop.domain.entity.point.Point
import com.hobbyloop.domain.repository.ad.AdRepository
import com.hobbyloop.domain.repository.point.PointRepository
import datasource.ad.AdDataSource
import datasource.point.PointDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import model.ad.toEntity
import model.point.toEntity
import javax.inject.Inject

class MockPointRepositoryImpl @Inject constructor(
    private val dataSource: PointDataSource
) : PointRepository{
    override fun getPointHistory(): Flow<Resource<Point>> {
        return dataSource.getPointHistory().map { result ->
            when (result) {
                is CustomResult.Success -> Resource.Success(result.data.toEntity() )
                is CustomResult.Error -> Resource.Error("result.error", null)
            }
        }
    }
}

class MockAdRepositoryImpl @Inject constructor(
    private val dataSource: AdDataSource
) : AdRepository {
    override fun getAds(): Flow<Resource<List<Ad>>> {
        return dataSource.getAds()
            .catch { e -> emit(CustomResult.Error(DataError.Network.UNKNOWN)) }
            .map { result ->
                when (result) {
                    is CustomResult.Success -> Resource.Success(result.data.map { it.toEntity() })
                    is CustomResult.Error -> Resource.Error("result.error", null)
                }
            }
    }
}