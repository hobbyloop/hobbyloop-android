package model.user

import com.hobbyloop.domain.entity.user.UserInfo
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val profileImageUrl: String?,
    val points: Int,
    val ticketCount: Int,
    val couponCount: Int
)

fun UserInfoResponse.toDomain() = UserInfo(
    name,
    nickname,
    phoneNumber,
    profileImageUrl?: "",
    points,
    ticketCount,
    couponCount,
)

