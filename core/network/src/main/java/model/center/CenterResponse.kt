package model.center

import com.hobbyloop.domain.entity.center.Center

data class CenterResponse(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val address: String,
    val isBookmarked: Boolean
)

fun CenterResponse.toEntity(): Center {
    return Center(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        address = this.address,
        isBookmarked = this.isBookmarked
    )
}
