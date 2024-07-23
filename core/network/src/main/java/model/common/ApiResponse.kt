package model.common

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T
)
