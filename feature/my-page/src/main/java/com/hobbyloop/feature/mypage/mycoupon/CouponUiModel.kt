package com.hobbyloop.feature.mypage.mycoupon

import com.hobbyloop.domain.entity.coupon.Coupon

data class CouponUiModel(
    val id: Int,
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

fun Coupon.toUiModel(): CouponUiModel {
    return CouponUiModel(
        id = this.id,
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
