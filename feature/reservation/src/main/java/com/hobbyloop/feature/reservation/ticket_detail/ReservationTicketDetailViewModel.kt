package com.hobbyloop.feature.reservation.ticket_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import com.hobbyloop.feature.reservation.ticket_detail.state.ReservationDetailState
import com.hobbyloop.feature.reservation.ticket_detail.state.ReservationTicketDetailIntent
import com.hobbyloop.feature.reservation.ticket_detail.state.ReservationTicketDetailSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ReservationTicketDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ContainerHost<ReservationDetailState, ReservationTicketDetailSideEffect> {

    private val args = ReservationCenterDetailArgs(savedStateHandle)
    private val ticketId = args.ticketId

    override val container: Container<ReservationDetailState, ReservationTicketDetailSideEffect> =
        container(
            initialState = ReservationDetailState.Loading,
            buildSettings = {
                this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    // TODO: 추후 에러 핸들링
                }
            }
        )

    init {
        handleIntent(ReservationTicketDetailIntent.LoadClasses)
    }

    fun handleIntent(intent: ReservationTicketDetailIntent) {
        when (intent) {
            is ReservationTicketDetailIntent.LoadClasses -> loadClasses()
            is ReservationTicketDetailIntent.SelectClassInfo -> selectClassInfo(intent.classInfo)
            is ReservationTicketDetailIntent.SelectWaitClassInfo -> selectWaitClassInfo(intent.classInfo)
            is ReservationTicketDetailIntent.ToggleInstructorDetailsVisible -> toggleInstructorDetailsVisible()
            is ReservationTicketDetailIntent.ResetInstructorDetailsVisible -> resetInstructorDetailsVisible()
            is ReservationTicketDetailIntent.ReserveClass -> reserveClass(intent.classInfo)
            is ReservationTicketDetailIntent.SetReservationBottomSheetOpenTicket -> setReservationBottomSheetOpen(
                intent.isOpen
            )

            is ReservationTicketDetailIntent.SetUpdating -> setUpdating(intent.isUpdating)
        }
    }

    private fun loadClasses() = intent {
        try {
            delay(2000)
            val classes = createDummyData()
            reduce {
                ReservationDetailState.Success(
                    centerName = "발란스 스튜디오",
                    classInfoList = classes
                )
            }
        } catch (e: Exception) {
            reduce {
                ReservationDetailState.Error(
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }

    private fun createDummyData(): List<Pair<Instructor, List<ClassInfo>>> {
        val instructors = listOf(
            Instructor(
                "윤지영",
                "https://src.hidoc.co.kr/image/lib/2022/3/28/1648455838120_0.jpg",
                listOf("2급 스포츠 지도사", "2급 스포츠 지도사", "2급 스포츠 지도사", "2급 스포츠 지도사")
            ),
            Instructor(
                "김지영",
                "https://exbody.kr/wp-content/uploads/2022/05/%ED%95%84%EB%9D%BC%ED%85%8C%EC%8A%A4-%EC%A0%95%EC%9D%98-%EC%97%AD%EC%82%AC-1-1536x1024.jpg",
                listOf("1급 스포츠 지도사", "1급 스포츠 지도사", "1급 스포츠 지도사", "1급 스포츠 지도사")
            )
        )

        val classes = listOf(
            listOf(
                ClassInfo(20, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(21, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(22, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(23, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(24, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(1, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(1, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(1, "2024-06-7 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(2, "2024-06-7 10:00 - 11:00", "고급 요가", "고급", 5, 5),
                ClassInfo(3, "2024-06-12 08:00 - 09:00", "아침 요가", "초급", 12, 12),
                ClassInfo(4, "2024-06-12 10:00 - 11:00", "고급 요가", "고급", 3, 6),
                ClassInfo(9, "2024-06-13 09:00 - 10:00", "바디 피트", "중급", 8, 10),
                ClassInfo(10, "2024-06-14 07:30 - 08:30", "명상 클래스", "초급", 15, 15),
                ClassInfo(11, "2024-06-21 18:00 - 19:00", "이브닝 요가", "중급", 9, 10)
            ),
            listOf(
                ClassInfo(1, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(5, "2024-06-10 08:00 - 09:00", "필라테스 입문", "초급", 8, 10),
                ClassInfo(6, "2024-06-10 11:00 - 12:00", "중급 필라테스", "중급", 6, 7),
                ClassInfo(7, "2024-06-12 08:00 - 09:00", "필라테스 입문", "초급", 7, 9),
                ClassInfo(8, "2024-06-12 11:00 - 12:00", "중급 필라테스", "중급", 4, 4),
                ClassInfo(12, "2024-06-13 12:00 - 13:00", "고급 필라테스", "고급", 5, 5),
                ClassInfo(13, "2024-06-13 15:00 - 16:00", "건강 요가", "초급", 10, 12),
                ClassInfo(14, "2024-06-21 10:00 - 11:00", "통증 관리 요가", "중급", 7, 8)
            )
        )

        return instructors.zip(classes)
    }

    private fun selectClassInfo(selectedClassInfo: ClassInfo) = intent {
        reduce {
            if (state is ReservationDetailState.Success) {
                (state as ReservationDetailState.Success).copy(selectedClassInfo = selectedClassInfo)
            } else {
                state
            }
        }
    }

    private fun selectWaitClassInfo(selectedWaitClassInfo: ClassInfo) = intent {
        reduce {
            if (state is ReservationDetailState.Success) {
                (state as ReservationDetailState.Success).copy(selectedWaitClassInfo = selectedWaitClassInfo)
            } else {
                state
            }
        }
    }

    private fun toggleInstructorDetailsVisible() = intent {
        reduce {
            if (state is ReservationDetailState.Success) {
                (state as ReservationDetailState.Success).copy(isInstructorDetailsVisible = !(state as ReservationDetailState.Success).isInstructorDetailsVisible)
            } else {
                state
            }
        }
    }

    private fun resetInstructorDetailsVisible() = intent {
        reduce {
            if (state is ReservationDetailState.Success) {
                (state as ReservationDetailState.Success).copy(isInstructorDetailsVisible = false)
            } else {
                state
            }
        }
    }

    private fun setReservationBottomSheetOpen(isOpen: Boolean) = intent {
        reduce {
            if (state is ReservationDetailState.Success) {
                (state as ReservationDetailState.Success).copy(isReservationBottomSheetOpen = isOpen)
            } else {
                state
            }
        }
    }

    private fun setUpdating(isUpdating: Boolean) = intent {
        reduce {
            if (state is ReservationDetailState.Success) {
                (state as ReservationDetailState.Success).copy(isUpdating = isUpdating)
            } else {
                state
            }
        }
    }

    private fun reserveClass(classInfo: ClassInfo) = intent {
        reduce {
            (state as? ReservationDetailState.Success)?.copy(isUpdating = true) ?: state
        }
        try {
            simulateReserveClass(classInfo)
            postSideEffect(ReservationTicketDetailSideEffect.ReservationTicketSuccess("Reservation successful"))
        } catch (e: Exception) {
            postSideEffect(
                ReservationTicketDetailSideEffect.ReservationTicketFailed(
                    e.message ?: "Unknown error"
                )
            )
        }
    }

    fun navigateToReservationClassDetail() = intent {
        postSideEffect(ReservationTicketDetailSideEffect.NavigateToReservationClassDetail(classId = (state as ReservationDetailState.Success).selectedClassInfo?.classId.toString()))
    }

    private suspend fun simulateReserveClass(classInfo: ClassInfo) = intent {
        // TODO: 서버에 예약 요청을 보내는 척하는 함수 호출(나중에 서버 통신 함수로 교체 해야함)
        delay(2000) // 서버 호출을 흉내내기 위해 2초 지연

        // 실제 서버 호출 로직이 이곳에 들어갈 예정이며 아래의 코드는 서버 성공을 가정하고 호출함
        reduce {
            if (state is ReservationDetailState.Success) {
                (state as ReservationDetailState.Success).copy(
                    isReservationBottomSheetOpen = false,
                    selectedClassInfo = null,
                    selectedWaitClassInfo = null,
                    isUpdating = false
                )
            } else {
                state
            }
        }
    }
}
