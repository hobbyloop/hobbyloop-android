package com.hobbyloop.domain.repository.coupon

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.coupon.Coupon
import kotlinx.coroutines.flow.Flow

interface CouponRepository {
    fun getCoupons(): Flow<CustomResult<List<Coupon>, DataError.Network>>
}