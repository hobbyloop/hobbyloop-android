package model.ad

import com.hobbyloop.domain.entity.ad.Ad

data class AdResponse(
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

fun AdResponse.toEntity(): Ad {
    return Ad(
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