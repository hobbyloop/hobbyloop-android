package com.hobbyloop.domain.model


/**
 * CenterUiModel 데이터 클래스는 시설 UI 정보를 담고 있습니다.
 *
 * @property centerId 센터의 고유 식별자 (유형: Long)
 * @property centerName 센터의 이름 (유형: String)
 * @property address 센터의 주소입 (유형: String)
 * @property logoImageUrl 센터의 로고 이미지 URL (유형: String)
 * @property price 센터의 이용 요금 (유형: Long)
 * @property score 센터의 평점 (유형: Double)
 * @property reviewCount 센터에 대한 리뷰 개수 (유형: Int)
 * @property refundable 예약 취소가 가능한지 여부 (유형: Boolean)
 * @property isBookMarked 사용자가 해당 센터를 즐겨찾기에 추가했는지 여부 (유형: Boolean)
 */
data class Center(
    val centerId: Long,
    val centerName: String,
    val address: String,
    val logoImageUrl: String,
    val price: Long,
    val score: Double,
    val reviewCount: Int,
    val refundable: Boolean,
    val isBookMarked: Boolean,
    val isRefundable: Boolean,
)
