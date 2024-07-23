package datasource.coupon

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.coupon.CouponResponse
import javax.inject.Inject

class MockCouponDataSourceImpl @Inject constructor() : CouponDataSource {
    override fun getCoupons(): Flow<CustomResult<List<CouponResponse>, DataError.Network>> {
        val mockCoupons = listOf(
            CouponResponse(
                memberCouponId = 1,
                minimumPurchaseAmount = 75000,
                maximumDiscountAmount = 25000,
                usableScope = 1,
                excludedCompaniesAndCenters = "하비루프 스튜디오 강남점, 필라피티 스튜디오 석촌점",
                expirationDateTime = "2024-06-30T12:27:15.543Z",
                discountType = 1,
                discountAmount = 20000,
                discountPercentage = 15,
                description = "첫구매 할인 쿠폰"
            ),
            CouponResponse(
                memberCouponId = 2,
                minimumPurchaseAmount = 75000,
                maximumDiscountAmount = 25000,
                usableScope = 1,
                excludedCompaniesAndCenters = "하비루프 스튜디오 강남점, 필라피티 스튜디오 석촌점",
                expirationDateTime = "2024-06-30T12:27:15.543Z",
                discountType = 1,
                discountAmount = 20000,
                discountPercentage = 15,
                description = "첫구매 할인 쿠폰"
            ),
            CouponResponse(
                memberCouponId = 3,
                minimumPurchaseAmount = 75000,
                maximumDiscountAmount = 25000,
                usableScope = 1,
                excludedCompaniesAndCenters = "하비루프 스튜디오 강남점, 필라피티 스튜디오 석촌점",
                expirationDateTime = "2024-06-30T12:27:15.543Z",
                discountType = 1,
                discountAmount = 20000,
                discountPercentage = 15,
                description = "첫구매 할인 쿠폰"
            )
        )
        return flow {
            emit(CustomResult.Success(mockCoupons))
        }
    }
}
