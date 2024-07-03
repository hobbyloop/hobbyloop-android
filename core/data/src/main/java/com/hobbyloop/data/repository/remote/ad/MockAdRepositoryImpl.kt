package com.hobbyloop.data.repository.remote.ad

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ad.Ad
import com.hobbyloop.domain.repository.ad.AdRepository
import datasource.ad.AdDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import model.ad.toEntity
import javax.inject.Inject

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