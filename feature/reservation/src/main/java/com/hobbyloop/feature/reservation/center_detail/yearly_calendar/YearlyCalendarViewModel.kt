package com.hobbyloop.feature.reservation.center_detail.yearly_calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo
import com.hobbyloop.feature.reservation.center_detail.model.Instructor
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.CalendarMonth
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelected
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.model.DaySelectedStatus
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.repository.DatesRepository
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.state.YearlyCalendarEvent
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.state.YearlyCalendarIntent
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.state.YearlyCalendarState
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.util.YearlyCalendarUtils.getCurrentDate
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.util.YearlyCalendarUtils.getCurrentMonthIndex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearlyCalendarViewModel @Inject constructor(
    private val datesRepository: DatesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        YearlyCalendarState(
            calendarYear = datesRepository.calendarYear,
            selectedMonthIndex = getCurrentMonthIndex(startYear = 2024),
            selectedMonth = datesRepository.calendarYear[getCurrentMonthIndex(startYear = 2024)],
        )
    )
    val uiState: StateFlow<YearlyCalendarState> = _uiState.asStateFlow()

    private var previousSelectedDay: DaySelected? = null

    init {
        /** TODO: 업데이트 방식 고민
         * flow - combine, stateIn()을 통해서 StateFlow로 변환해서 사용하려고 했지만,
         * 이렇게 하게 되면 MutableStateFlow가 아니라 update() 함수를 사용하지 못하는 단점이 존재..
         * ==> 일단 아래와 같이 YearlyCalendarState 내부에 프로퍼티의 값을 가져와 업데이트 방식을 함
         */
        // 선택된 월 인덱스가 변경될 때마다 선택된 월 정보를 업데이트
        viewModelScope.launch {
            uiState.collectLatest { state ->
                val previousOrNextMonthData = state.calendarYear[state.selectedMonthIndex]
                val selectedDayInfo = state.selectedDay

                _uiState.update {
                    it.copy(selectedMonth = updateSelectedMonthState(previousOrNextMonthData, selectedDayInfo))
                }
            }
        }
    }

    fun handleIntent(intent: YearlyCalendarIntent) {
        when (intent) {
            is YearlyCalendarIntent.LoadReservations -> loadReservations(intent.classInfo)
            is YearlyCalendarIntent.SelectDay -> selectDay(intent.daySelected)
            YearlyCalendarIntent.NextMonth -> nextMonth()
            YearlyCalendarIntent.PreviousMonth -> previousMonth()
        }
    }

    private fun handleEvent(event: YearlyCalendarEvent) {
        viewModelScope.launch {
            when (event) {
                is YearlyCalendarEvent.ReservationsLoaded -> {
                    delay(2000)
                    _uiState.update {
                        it.copy(
                            calendarYear = it.calendarYear.map { month ->
                                month.assignReservations(event.classInfo)
                                month
                            },
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
                is YearlyCalendarEvent.LoadFailed -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = event.error
                        )
                    }
                }
            }
        }

    }

    private fun loadReservations(classInfo: List<Pair<Instructor, List<ClassInfo>>>) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                handleEvent(YearlyCalendarEvent.ReservationsLoaded(classInfo))
                if (_uiState.value.selectedDay == null) {
                    val currentDate = getCurrentDate()
                    val matchingMonth = _uiState.value.calendarYear.find {
                        it.year == currentDate.year && it.month == currentDate.month
                    }
                    val matchingDay = matchingMonth?.calendarDays?.find { it.day == currentDate.day }
                    _uiState.update {
                        it.copy(
                            selectedDay = DaySelected(
                                year = currentDate.year,
                                month = currentDate.month,
                                day = currentDate.day,
                                classInfoList = matchingDay?.reservation
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                handleEvent(YearlyCalendarEvent.LoadFailed(e.message ?: "Unknown error"))
            }
        }
    }

    private fun selectDay(daySelected: DaySelected) {
        _uiState.update {
            it.copy(selectedDay = daySelected)
        }
    }

    private fun nextMonth() {
        _uiState.update {
            if (it.selectedMonthIndex < it.calendarYear.size - 1) {
                it.copy(selectedMonthIndex = it.selectedMonthIndex + 1)
            } else {
                it
            }
        }
    }

    private fun previousMonth() {
        _uiState.update {
            if (it.selectedMonthIndex > 0) {
                it.copy(selectedMonthIndex = it.selectedMonthIndex - 1)
            } else {
                it
            }
        }
    }

    private fun updateSelectedDayState(daySelected: DaySelected?) {
        _uiState.update { currentState ->
            val updatedDays = currentState.selectedMonth.calendarDays.map { calendarDay ->
                when {
                    // 현재 선택된 날짜가 해당하는 경우
                    daySelected?.month == currentState.selectedMonth.calendarTypeMonth + 1
                            && calendarDay.day == daySelected.day
                            && currentState.selectedMonth.year == daySelected.year -> {
                        calendarDay.copy(status = DaySelectedStatus.Selected)
                    }

                    // 이전에 선택된 날짜를 선택 해제 상태로 변경하는 경우
                    previousSelectedDay?.month == currentState.selectedMonth.calendarTypeMonth + 1
                            && calendarDay.day == previousSelectedDay?.day
                            && currentState.selectedMonth.year == daySelected?.year -> {
                        calendarDay.copy(status = DaySelectedStatus.NoSelected)
                    }

                    // 그 외의 경우, 상태를 그대로 유지
                    else -> calendarDay
                }
            }

            // 업데이트된 날짜 리스트를 사용하여 현재 달의 상태를 복사 및 업데이트
            currentState.copy(
                selectedMonth = currentState.selectedMonth.copy(calendarDays = updatedDays),
                selectedDay = daySelected
            )
        }

        // 이전에 선택된 날짜를 현재 선택된 날짜로 갱신
        previousSelectedDay = daySelected
    }

    private fun updateSelectedMonthState(
        monthData: CalendarMonth,
        selectedDayInfo: DaySelected?
    ): CalendarMonth {
        // 선택된 날짜 정보가 없는 경우 그대로 반환
        if (selectedDayInfo == null) {
            return monthData
        }

        // 선택된 날짜가 다음 월에 포함되는지 확인
        val isSameMonth =
            monthData.year == selectedDayInfo.year && monthData.month == selectedDayInfo.month

        return if (isSameMonth) {
            // 날짜 상태를 업데이트한 월 정보 반환
            val updatedDays = monthData.calendarDays.map { calendarDay ->
                if (calendarDay.day == selectedDayInfo.day) {
                    // 선택된 상태로 변경
                    calendarDay.copy(status = DaySelectedStatus.Selected)
                } else {
                    calendarDay
                }
            }
            monthData.copy(calendarDays = updatedDays)
        } else {
            // 선택된 날짜가 다른 월에 속하는 경우 월 정보만 그대로 반환
            monthData
        }
    }
}
