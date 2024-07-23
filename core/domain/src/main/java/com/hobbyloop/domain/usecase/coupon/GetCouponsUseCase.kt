package com.hobbyloop.domain.usecase.coupon

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.coupon.Coupon
import com.hobbyloop.domain.repository.coupon.CouponRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCouponsUseCase @Inject constructor(
    private val couponRepository: CouponRepository
) {
    operator fun invoke(): Flow<CustomResult<List<Coupon>, DataError.Network>> {
        return couponRepository.getCoupons()
    }
}
