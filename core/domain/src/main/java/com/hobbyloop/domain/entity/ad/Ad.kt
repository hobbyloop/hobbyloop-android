package com.hobbyloop.domain.entity.ad

data class Ad(
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
