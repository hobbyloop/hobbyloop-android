package com.hobbyloop.feature.reservation.ticket_detail.state

sealed interface ReservationTicketDetailSideEffect {
    class ReservationTicketSuccess(val message: String) : ReservationTicketDetailSideEffect
    class ReservationTicketFailed(val error: String) : ReservationTicketDetailSideEffect
    class NavigateToReservationClassDetail(val classId: String) : ReservationTicketDetailSideEffect
}
