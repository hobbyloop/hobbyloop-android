package com.hobbyloop.domain.entity.ticket

import com.hobbyloop.domain.entity.center.Center

data class HotTicket(
    val center: Center,
    val ticket: Ticket
)