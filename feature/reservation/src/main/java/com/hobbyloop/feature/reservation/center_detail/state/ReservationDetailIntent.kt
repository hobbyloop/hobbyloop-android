package com.hobbyloop.feature.reservation.center_detail.state

import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo

sealed class ReservationDetailIntent {
    data object LoadClasses : ReservationDetailIntent()
    data class SelectClassInfo(val classInfo: ClassInfo) : ReservationDetailIntent()
    data class SelectWaitClassInfo(val classInfo: ClassInfo) : ReservationDetailIntent()
    data object ToggleInstructorDetailsVisible : ReservationDetailIntent()
    data object ResetInstructorDetailsVisible : ReservationDetailIntent()
    data class ReserveClass(val classInfo: ClassInfo) : ReservationDetailIntent()
    data class SetReservationBottomSheetOpen(val isOpen: Boolean) : ReservationDetailIntent()
    data class SetUpdating(val isUpdating: Boolean) : ReservationDetailIntent()
}
