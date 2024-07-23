package model.coupon

import com.hobbyloop.domain.entity.coupon.Coupon

data class CouponResponse(
    val memberCouponId: Int,
    val minimumPurchaseAmount: Int,
    val maximumDiscountAmount: Int,
    val usableScope: Int,
    val excludedCompaniesAndCenters: String,
    val expirationDateTime: String,
    val discountType: Int,
    val discountAmount: Int,
    val discountPercentage: Int,
    val description: String
)

fun CouponResponse.toDomain(): Coupon {
    return Coupon(
        id = this.memberCouponId,
        minimumPurchaseAmount = this.minimumPurchaseAmount,
        maximumDiscountAmount = this.maximumDiscountAmount,
        usableScope = this.usableScope,
        excludedCompaniesAndCenters = this.excludedCompaniesAndCenters,
        expirationDateTime = this.expirationDateTime,
        discountType = this.discountType,
        discountAmount = this.discountAmount,
        discountPercentage = this.discountPercentage,
        description = this.description
    )
}