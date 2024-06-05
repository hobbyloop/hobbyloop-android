package com.hobbyloop.feature.reservation.ticket_detail.state

import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor

sealed class ReservationTicketDetailEvent {
    data class ClassesLoaded(val classInfoList: List<Pair<Instructor, List<ClassInfo>>>) : ReservationTicketDetailEvent()
    data class LoadFailed(val error: String) : ReservationTicketDetailEvent()
    data class ReservationTicketSuccess(val message: String) : ReservationTicketDetailEvent()
    data class ReservationTicketFailed(val error: String) : ReservationTicketDetailEvent()
}
