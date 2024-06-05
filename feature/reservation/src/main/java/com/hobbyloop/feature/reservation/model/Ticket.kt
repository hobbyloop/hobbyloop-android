package com.hobbyloop.feature.reservation.model

data class Ticket(
    val ticketInfo: Pair<CenterInfo, List<ClassInfo>>
)
