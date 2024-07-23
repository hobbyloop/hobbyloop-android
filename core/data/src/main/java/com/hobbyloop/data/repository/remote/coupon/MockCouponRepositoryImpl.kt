package com.hobbyloop.data.repository.remote.coupon

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.coupon.Coupon
import com.hobbyloop.domain.repository.coupon.CouponRepository
import datasource.coupon.CouponDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import model.coupon.toDomain
import javax.inject.Inject

class MockCouponRepositoryImpl @Inject constructor(
    private val dataSource: CouponDataSource
) : CouponRepository {
    override fun getCoupons(): Flow<CustomResult<List<Coupon>, DataError.Network>> {
        return dataSource.getCoupons()
            .catch { e ->
                emit(CustomResult.Error(DataError.Network.UNKNOWN))
            }
            .map { result ->
                when (result) {
                    is CustomResult.Success -> CustomResult.Success(result.data.map { it.toDomain() })
                    is CustomResult.Error -> CustomResult.Error(result.error)
                }
            }
    }
}