package com.hobbyloop.domain.entity.coupon

data class Coupon(
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
