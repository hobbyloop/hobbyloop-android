package com.hobbyloop.domain.entity.ticket

import com.hobbyloop.domain.entity.center.Center

data class RecommendTicket(
    val center: Center,
    val ticket: Ticket
)