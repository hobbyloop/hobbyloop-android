package com.hobbyloop.feature.reservation.model

import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo

data class Ticket(
    val ticketInfo: Pair<CenterInfo, List<ClassInfo>>
)
