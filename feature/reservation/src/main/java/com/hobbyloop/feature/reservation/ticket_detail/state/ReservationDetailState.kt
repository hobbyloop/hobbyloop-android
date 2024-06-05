package com.hobbyloop.feature.reservation.ticket_detail.state

import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor

sealed class ReservationDetailState {
    data object Loading : ReservationDetailState() //  화면 전체를 로딩 상태로 표시할 때 사용되는 상태
    data class Success(
        val isUpdating: Boolean = false, // 부분적인 서버 요청(수업 대기 신청 서버 요청) 시 로딩 상태를 표시하기 위한 변수
        val classInfoList: List<Pair<Instructor, List<ClassInfo>>>,
        val selectedClassInfo: ClassInfo? = null,
        val selectedWaitClassInfo: ClassInfo? = null,
        val isInstructorDetailsVisible: Boolean = false,
        val isReservationBottomSheetOpen: Boolean = false
    ) : ReservationDetailState()
    data class Error(val errorMessage: String) : ReservationDetailState()
}
