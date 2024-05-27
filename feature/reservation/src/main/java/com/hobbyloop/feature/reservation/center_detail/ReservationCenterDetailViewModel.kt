package com.hobbyloop.feature.reservation.center_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo
import com.hobbyloop.feature.reservation.center_detail.model.Instructor
import com.hobbyloop.feature.reservation.center_detail.state.ReservationDetailEvent
import com.hobbyloop.feature.reservation.center_detail.state.ReservationDetailIntent
import com.hobbyloop.feature.reservation.center_detail.state.ReservationDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationCenterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = ReservationCenterDetailArgs(savedStateHandle)
    private val centerId = args.centerId

    private val _uiState = MutableStateFlow<ReservationDetailState>(ReservationDetailState.Loading)
    val uiState: StateFlow<ReservationDetailState> = _uiState.asStateFlow()

    init {
        handleIntent(ReservationDetailIntent.LoadClasses)
    }

    fun handleIntent(intent: ReservationDetailIntent) {
        when (intent) {
            is ReservationDetailIntent.LoadClasses -> loadClasses()
            is ReservationDetailIntent.SelectClassInfo -> selectClassInfo(intent.classInfo)
            is ReservationDetailIntent.SelectWaitClassInfo -> selectWaitClassInfo(intent.classInfo)
            is ReservationDetailIntent.ToggleInstructorDetailsVisible -> toggleInstructorDetailsVisible()
            is ReservationDetailIntent.ResetInstructorDetailsVisible -> resetInstructorDetailsVisible()
            is ReservationDetailIntent.ReserveClass -> reserveClass(intent.classInfo)
            is ReservationDetailIntent.SetReservationBottomSheetOpen -> setReservationBottomSheetOpen(intent.isOpen)
            is ReservationDetailIntent.SetUpdating -> setUpdating(intent.isUpdating)
        }
    }

    private fun handleEvent(event: ReservationDetailEvent) {
        when (event) {
            is ReservationDetailEvent.ClassesLoaded -> {
                _uiState.update {
                    ReservationDetailState.Success(
                        classInfoList = event.classInfoList
                    )
                }
            }
            is ReservationDetailEvent.LoadFailed -> {
                _uiState.update {
                    ReservationDetailState.Error(
                        errorMessage = event.error
                    )
                }
            }
            is ReservationDetailEvent.ReservationSuccess -> {
                handleIntent(ReservationDetailIntent.SetUpdating(isUpdating = false))
                _uiState.update { currentState ->
                    if (currentState is ReservationDetailState.Success) {
                        currentState.copy(
                            isReservationBottomSheetOpen = false,
                            selectedClassInfo = null,
                            selectedWaitClassInfo = null
                        )
                    } else {
                        currentState
                    }
                }
            }
            is ReservationDetailEvent.ReservationFailed -> {

            }
        }
    }

    private fun loadClasses() {
        viewModelScope.launch {
            _uiState.update { ReservationDetailState.Loading }
            try {
                val classes = createDummyData()
                delay(3000) // 로딩 화면 확인을 위한 딜레이, 추후 제거 할 것
                handleEvent(ReservationDetailEvent.ClassesLoaded(classes))
            } catch (e: Exception) {
                handleEvent(ReservationDetailEvent.LoadFailed(e.message ?: "Unknown error"))
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
                ClassInfo(1, "2024-05-7 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(2, "2024-05-7 10:00 - 11:00", "고급 요가", "고급", 5, 5),
                ClassInfo(3, "2024-05-12 08:00 - 09:00", "아침 요가", "초급", 12, 12),
                ClassInfo(4, "2024-05-12 10:00 - 11:00", "고급 요가", "고급", 3, 6),
                ClassInfo(9, "2024-05-13 09:00 - 10:00", "바디 피트", "중급", 8, 10),
                ClassInfo(10, "2024-05-14 07:30 - 08:30", "명상 클래스", "초급", 15, 15),
                ClassInfo(11, "2024-05-21 18:00 - 19:00", "이브닝 요가", "중급", 9, 10)
            ),
            listOf(
                ClassInfo(5, "2024-05-10 08:00 - 09:00", "필라테스 입문", "초급", 8, 10),
                ClassInfo(6, "2024-05-10 11:00 - 12:00", "중급 필라테스", "중급", 6, 7),
                ClassInfo(7, "2024-05-12 08:00 - 09:00", "필라테스 입문", "초급", 7, 9),
                ClassInfo(8, "2024-05-12 11:00 - 12:00", "중급 필라테스", "중급", 4, 4),
                ClassInfo(12, "2024-05-13 12:00 - 13:00", "고급 필라테스", "고급", 5, 5),
                ClassInfo(13, "2024-05-13 15:00 - 16:00", "건강 요가", "초급", 10, 12),
                ClassInfo(14, "2024-05-21 10:00 - 11:00", "통증 관리 요가", "중급", 7, 8)
            )
        )

        return instructors.zip(classes)
    }

    private fun selectClassInfo(selectedClassInfo: ClassInfo) {
        _uiState.update { currentState ->
            if (currentState is ReservationDetailState.Success) {
                currentState.copy(selectedClassInfo = selectedClassInfo)
            } else {
                currentState
            }
        }
    }

    private fun selectWaitClassInfo(selectedWaitClassInfo: ClassInfo) {
        _uiState.update { currentState ->
            if (currentState is ReservationDetailState.Success) {
                currentState.copy(selectedWaitClassInfo = selectedWaitClassInfo)
            } else {
                currentState
            }
        }
    }

    private fun toggleInstructorDetailsVisible() {
        _uiState.update { currentState ->
            if (currentState is ReservationDetailState.Success) {
                currentState.copy(isInstructorDetailsVisible = !currentState.isInstructorDetailsVisible)
            } else {
                currentState
            }
        }
    }

    private fun resetInstructorDetailsVisible() {
        _uiState.update { currentState ->
            if (currentState is ReservationDetailState.Success) {
                currentState.copy(isInstructorDetailsVisible = false)
            } else {
                currentState
            }
        }
    }

    private fun setReservationBottomSheetOpen(isOpen: Boolean) {
        _uiState.update { currentState ->
            if (currentState is ReservationDetailState.Success) {
                currentState.copy(isReservationBottomSheetOpen = isOpen)
            } else {
                currentState
            }
        }
    }

    private fun setUpdating(isUpdating: Boolean) {
        _uiState.update { currentState ->
            if (currentState is ReservationDetailState.Success) {
                currentState.copy(isUpdating = isUpdating)
            } else {
                currentState
            }
        }
    }


    private fun reserveClass(classInfo: ClassInfo) {
        viewModelScope.launch {
            try {
                // TODO: 서버에 예약 요청을 보내는 척하는 함수 호출(나중에 서버 통신 함수로 교체 해야함)
                simulateReserveClass(classInfo)
            } catch (e: Exception) {
                handleEvent(ReservationDetailEvent.ReservationFailed(e.message ?: "Unknown error"))
            }
        }
    }

    private suspend fun simulateReserveClass(classInfo: ClassInfo) {
        handleIntent(ReservationDetailIntent.SetUpdating(isUpdating = true))
        delay(2000) // 서버 호출을 흉내내기 위해 2초 지연

        // 실제 서버 호출 로직이 이곳에 들어갈 예정이며 아래의 코드는 서버 성공을 가정하고 호출함
        handleEvent(ReservationDetailEvent.ReservationSuccess("Reservation successful"))
    }
}
