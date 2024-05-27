package com.hobbyloop.feature.reservation.center_detail.state

import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo
import com.hobbyloop.feature.reservation.center_detail.model.Instructor

sealed class ReservationDetailEvent {
    data class ClassesLoaded(val classInfoList: List<Pair<Instructor, List<ClassInfo>>>) : ReservationDetailEvent()
    data class LoadFailed(val error: String) : ReservationDetailEvent()
    data class ReservationSuccess(val message: String) : ReservationDetailEvent()
    data class ReservationFailed(val error: String) : ReservationDetailEvent()
}
