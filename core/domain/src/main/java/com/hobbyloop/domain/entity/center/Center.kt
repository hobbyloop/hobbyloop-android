package com.hobbyloop.domain.entity.center

data class Center(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val address: String,
    val isBookmarked: Boolean
)
