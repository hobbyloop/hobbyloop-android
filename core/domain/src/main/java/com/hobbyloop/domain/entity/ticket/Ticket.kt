package com.hobbyloop.domain.entity.ticket

data class Ticket(
    val id: Int,
    val name: String,
    val score: Double,
//    val isRefundable: Boolean,
    val reviewCount: Int,
    val category: String,
    val price: Int,
)