package com.hobbyloop.domain.entity.ticket

import com.hobbyloop.domain.entity.center.CenterInfo
import com.hobbyloop.domain.entity.class_info.ClassInfo

data class TicketInfo(
    val ticketInfo: Pair<CenterInfo, List<ClassInfo>>
)
