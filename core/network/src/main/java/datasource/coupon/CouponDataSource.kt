package datasource.coupon

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.flow.Flow
import model.coupon.CouponResponse

interface CouponDataSource {
    fun getCoupons(): Flow<CustomResult<List<CouponResponse>, DataError.Network>>
}
