package com.hobbyloop.feature.reservation.ticket_detail.state

import com.hobbyloop.domain.entity.class_info.ClassInfo

sealed class ReservationTicketDetailIntent {
    data object LoadClasses : ReservationTicketDetailIntent()
    data class SelectClassInfo(val classInfo: ClassInfo) : ReservationTicketDetailIntent()
    data class SelectWaitClassInfo(val classInfo: ClassInfo) : ReservationTicketDetailIntent()
    data object ToggleInstructorDetailsVisible : ReservationTicketDetailIntent()
    data object ResetInstructorDetailsVisible : ReservationTicketDetailIntent()
    data class ReserveClass(val classInfo: ClassInfo) : ReservationTicketDetailIntent()
    data class SetReservationBottomSheetOpenTicket(val isOpen: Boolean) : ReservationTicketDetailIntent()
    data class SetUpdating(val isUpdating: Boolean) : ReservationTicketDetailIntent()
}
