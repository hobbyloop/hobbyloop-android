package com.hobbyloop.feature.home.model

import com.hobbyloop.domain.entity.ad.Ad

data class AdUiModel(
    val id: Int,
    val centerId: Int,
    val campaignName: String,
    val centerName: String,
    val content: String,
    val keyword: String,
    val bannerImageUrl: String,
    val adStart: String,
    val adEnd: String,
    val adRank: Int,
)

fun Ad.toUiModel(): AdUiModel {
    return AdUiModel(
        id = this.id,
        centerId = this.centerId,
        campaignName = this.campaignName,
        centerName = this.centerName,
        content = this.content,
        keyword = this.keyword,
        bannerImageUrl = this.bannerImageUrl,
        adStart = this.adStart,
        adEnd = this.adEnd,
        adRank = this.adRank
    )
}