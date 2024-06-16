package com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar

import androidx.lifecycle.ViewModel
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.CalendarMonth
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.DaySelected
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.DaySelectedStatus
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.repository.DatesRepository
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.state.YearlyCalendarEvent
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.state.YearlyCalendarIntent
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.state.YearlyCalendarSideEffect
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.state.YearlyCalendarState
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.util.YearlyCalendarUtils.getCurrentDate
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.util.YearlyCalendarUtils.getCurrentMonthIndex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class YearlyReservationCalendarViewModel @Inject constructor(
    private val datesRepository: DatesRepository
) : ViewModel(), ContainerHost<YearlyCalendarState, YearlyCalendarSideEffect> {

    override val container: Container<YearlyCalendarState, YearlyCalendarSideEffect> = container(
        initialState = YearlyCalendarState(
            calendarYear = datesRepository.calendarYear,
            selectedMonthIndex = getCurrentMonthIndex(startYear = 2024),
            selectedMonth = datesRepository.calendarYear[getCurrentMonthIndex(startYear = 2024)],
        ),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent { postSideEffect(YearlyCalendarSideEffect.ShowError(throwable.message ?: "Unknown error")) }
            }
        }
    )

    init {
        setCurrentDateAsSelectedIfNone()
        observeStateChanges()
    }

    fun handleIntent(intent: YearlyCalendarIntent) = intent {
        when (intent) {
            is YearlyCalendarIntent.LoadReservations -> loadReservations(intent.classInfo)
            is YearlyCalendarIntent.SelectDay -> selectDay(intent.daySelected)
            YearlyCalendarIntent.NextMonth -> nextMonth()
            YearlyCalendarIntent.PreviousMonth -> previousMonth()
        }
    }

    private fun handleEvent(event: YearlyCalendarEvent) = intent {
        when (event) {
            is YearlyCalendarEvent.ReservationsLoaded -> {
                reduce {
                    // 가져온 수업 정보를 캘린더에 반영하도록 하는 로직
                    val updatedState = state.copy(
                        calendarYear = state.calendarYear.map { month ->
                            month.assignReservations(event.classInfo)
                            month
                        },
                        isLoading = false,
                        errorMessage = null
                    )

                    /**
                     * 선택된 날짜(selectedDay)가 없는 경우와 있는 경우를 분기하여,
                     * 수업 일정을 반영한 달력 데이터(calendarYear)를 기반으로,
                     * 년/월/일을 비교하여 해당 날짜의 수업 일정을 반영한 달력 데이터인
                     * calendarYear 프로퍼티의 reservation을 선택된 날짜(selectedDay)의 classInfoList 프로퍼티에 새롭게 업데이트 함
                     */
                    val newSelectedDay = if (updatedState.selectedDay == null) { // 선택된 날짜(selectedDay)가 없는 경우
                        /**
                         * 선택된 날짜가 없으므로 현재 날짜의 데이터를 가져와,
                         * 수업 일정이 반영된 달력 데이터(calendarYear)를 기반으로 현재 날짜의 년/월/일을 비교하여
                         * (수업 일정을 반영한 달력 데이터에 포함된) 해당 날짜의 reservation을 선택된 날짜(selectedDay)의 classInfoList 프로퍼티에 업데이트함
                         */
                        val currentDate = getCurrentDate()
                        val matchingMonth = updatedState.calendarYear.find {
                            it.year == currentDate.year && it.month == currentDate.month
                        }
                        val matchingDay = matchingMonth?.calendarDays?.find { it.day == currentDate.day }
                        DaySelected(
                            year = currentDate.year,
                            month = currentDate.month,
                            day = currentDate.day,
                            classInfoList = matchingDay?.reservation
                        )
                    } else { // 선택된 날짜가 있는 경우(selectedDay)
                        /**
                         * 선택된 날짜가 있으므로 선택한 날짜의 데이터를 가져와,
                         * 수업 일정이 반영된 달력 데이터(calendarYear)를 기반으로 선택한 날짜의 년/월/일을 비교하여
                         * (수업 일정을 반영한 달력 데이터에 포함된) 해당 날짜의 reservation을 선택된 날짜(selectedDay)의 classInfoList 프로퍼티에 업데이트함
                         */
                        val selectedDay = updatedState.selectedDay
                        val matchingMonth = updatedState.calendarYear.find {
                            it.year == selectedDay.year && it.month == selectedDay.month
                        }
                        val matchingDay = matchingMonth?.calendarDays?.find { it.day == selectedDay.day }
                        selectedDay.copy(classInfoList = matchingDay?.reservation)
                    }

                    // 최종적으로 생성된 선택된 날짜 데이터(newSelectedDay)를 UiState의 selectedDay 프로퍼티에 업데이트하여 현재 선택된 날짜의 수업 정보를 반영한다.
                    updatedState.copy(selectedDay = newSelectedDay)
                }
            }
            is YearlyCalendarEvent.LoadFailed -> {
                reduce {
                    state.copy(
                        isLoading = false,
                        errorMessage = event.error
                    )
                }
            }
        }
    }

    /**
     * 예약 정보를 로드하는 함수.
     * - 이 함수는 수업 정보를 받아와서 해당 수업 정보를 캘린더에 반영(업데이트) 될 수 있도록 도와주는 YearlyCalendarEvent.ReservationsLoaded()를 호출함
     * - 예외 발생 시, YearlyCalendarEvent.LoadFailed 이벤트를 처리함.
     *
     * @param classInfo 수업 정보 리스트 (Instructor와 List<ClassInfo>의 Pair 리스트)
     */
    private fun loadReservations(classInfo: List<Pair<Instructor, List<ClassInfo>>>) = intent {
        try {
            delay(2000) // TODO 예약 정보 로드를 할 때 걸리는 시간 연출을 위한 delay 추후 삭제해야함.

            // 예약 정보 로드 이벤트 처리
            handleEvent(YearlyCalendarEvent.ReservationsLoaded(classInfo))
        } catch (e: Exception) {
            // 예외 발생 시 로드 실패 이벤트 처리
            handleEvent(YearlyCalendarEvent.LoadFailed(e.message ?: "Unknown error"))
        }
    }

    /**
     * UiState의 stateFlow를 수집하여 selectedMonthIndex(선택된 달의 인덱스) + 선택된 날의 정보(selectedDay)를 기반으로 selectedMonth(선택된 달)의 데이터를 업데이트하는 함수.
     * - 이 함수는 ViewModel이 초기화될 때(init 블록에서) 호출되어 상태 변화를 관찰하고 처리함.
     */
    private fun observeStateChanges() = intent {
        val stateFlow = container.stateFlow
        stateFlow.collectLatest { state ->
            val previousOrNextMonthData = state.calendarYear[state.selectedMonthIndex]
            val selectedDayInfo = state.selectedDay

            reduce {
                state.copy(
                    selectedMonth = updateSelectedMonthState(previousOrNextMonthData, selectedDayInfo)
                )
            }
        }
    }

    /**
     * 선택된 날짜가 없을 경우, 현재 날짜를 선택된 날짜로 설정하는 함수.
     * (해당 함수의 목적은, 화면에 첫 진입했을 때 현재의 날짜로 선택된 날짜를 할당을 하기 위해 사용함)
     *
     * - 이 함수는 현재 상태에서 선택된 날짜(selectedDay)가 null인 경우, 현재 날짜를 가져와서 선택된 날짜의 데이터를 업데이트함.
     * - 화면에 첫 진입했을 때 현재의 날짜로 선택된 날짜를 할당을 해야 하기에, 이 함수는 ViewModel이 초기화될 때(init 블록에서) 호출됨.
     */
    private fun setCurrentDateAsSelectedIfNone() = intent {
        if (state.selectedDay == null) {
            val currentDate = getCurrentDate()
            reduce {
                state.copy(
                    selectedDay = DaySelected(
                        year = currentDate.year,
                        month = currentDate.month,
                        day = currentDate.day,
                    )
                )
            }
        }
    }

    /**
     * 선택된 날짜의 데이터를 업데이트하는 함수.
     *
     * 이 함수는 UiState의 selectedDay 프로퍼티를 업데이트합니다.
     *
     * 1. UiState의 selectedDay 프로퍼티가 업데이트되면,
     * 2. observeStateChanges() 함수에서 해당 변화를 감지합니다.
     * 3. updateSelectedMonthState() 함수를 통해 선택된 날짜의 상태를 반영한 새로운 CalendarMonth 데이터를 UiState의 selectedMonth 프로퍼티에 할당합니다.
     *
     * 이를 통해 선택된 날짜의 상태가 현재 선택된 달의 데이터에 반영되게 할 수 있음.
     */
    private fun selectDay(daySelected: DaySelected) = intent {
        reduce {
            state.copy(selectedDay = daySelected)
        }
    }

    /**
     * 다음 달로 이동하기 위한 함수.
     *
     * 이 함수는 UiState의 selectedMonthIndex 프로퍼티를 +1 증가시켜 다음 달로 이동함.
     *  1. 현재 선택된 달의 인덱스(selectedMonthIndex)를 +1 증가시켜,
     *  2. observeStateChanges() 함수에서 해당 인덱스 변화를 감지하여
     *  3. selectedMonth(선택된 달)의 데이터를 업데이트함.
     */
    private fun nextMonth() = intent {
        reduce {
            if (state.selectedMonthIndex < state.calendarYear.size - 1) {
                state.copy(selectedMonthIndex = state.selectedMonthIndex + 1)
            } else {
                state
            }
        }
    }

    /**
     * 이전 달로 이동하기 위한 함수.
     *
     * 이 함수는 UiState의 selectedMonthIndex 프로퍼티를 -1 감소시켜 다음 달로 이동함.
     *  1. 현재 선택된 달의 인덱스(selectedMonthIndex)를 -1 감소시켜,
     *  2. observeStateChanges() 함수에서 해당 인덱스 변화를 감지하여
     *  3. selectedMonth(선택된 달)의 데이터를 업데이트함.
     */
    private fun previousMonth() = intent {
        reduce {
            if (state.selectedMonthIndex > 0) {
                state.copy(selectedMonthIndex = state.selectedMonthIndex - 1)
            } else {
                state
            }
        }
    }

    /**
     * 선택된 달의 데이터(monthData)와 선택된 날짜의 정보(selectedDayInfo)를 받아,
     * 선택된 날짜의 상태를 반영한 새로운 CalendarMonth 데이터를 반환하는 함수.
     *
     * 이 함수는 이전 달이나 다음 달로 이동할 때, 선택된 날짜의 상태를 유지하도록 합니다.
     * 만약 선택된 날짜가 주어진 달에 속하지 않는다면, 전달된 달의 데이터를 그대로 반환합니다.
     * 선택된 날짜가 해당 달에 속하는 경우, 해당 날짜의 상태를 선택된 상태(Selected)로 변경합니다.
     *
     * 이를 통해 달을 변경할 때, 선택된 날짜의 데이터와 비교하여 일치하는 날짜 데이터가 있으면
     * 해당 달의 데이터 중 일치하는 날짜의 상태를 선택된 상태로 변경하여, 달이 변경되어도 선택된 날짜의
     * 상태를 동적으로 유지할 수 있습니다.
     *
     * @param monthData 현재 선택된 달의 CalendarMonth 데이터
     * @param selectedDayInfo 선택된 날짜 정보 (DaySelected)
     *
     * [구체적인 로직]
     * - 주어진 선택된 날짜 정보(selectedDayInfo)가 해당 달(monthData)에 속하는지 확인합니다.
     * - 선택된 날짜가 해당 달에 속한다면, 달력의 일(day) 데이터를 순회하며 선택된 날짜에 해당하는 데이터를 업데이트합니다.
     * - 이때, 선택된 날짜에 해당하는 날(calendarDay)의 상태를 DaySelectedStatus.Selected로 변경합니다.
     * - 선택된 날짜가 해당 달에 속하지 않는다면, 달의 데이터(monthData)를 그대로 반환합니다.
     *
     * 최종적으로, 반환값은 업데이트된 달의 CalendarMonth 데이터입니다.
     */
    private fun updateSelectedMonthState(
        monthData: CalendarMonth,
        selectedDayInfo: DaySelected?
    ): CalendarMonth {
        // 선택된 날짜 정보가 없으면 현재 달 데이터를 그대로 반환
        if (selectedDayInfo == null) {
            return monthData
        }

        // 선택된 날짜가 현재 달에 포함되는지 확인
        val isSameMonth = monthData.year == selectedDayInfo.year && monthData.month == selectedDayInfo.month

        // 선택된 날짜가 현재 달에 포함되면 상태 업데이트, 그렇지 않으면 현재 달 데이터를 그대로 반환
        return if (isSameMonth) {
            // 달력의 일 데이터를 순회하면서 선택된 날짜의 상태를 업데이트
            val updatedDays = monthData.calendarDays.map { calendarDay ->
                if (calendarDay.day == selectedDayInfo.day) {
                    // 선택된 날짜의 상태를 Selected로 변경
                    calendarDay.copy(status = DaySelectedStatus.Selected)
                } else {
                    calendarDay
                }
            }
            // 업데이트된 일 데이터를 포함한 새로운 달 데이터 반환
            monthData.copy(calendarDays = updatedDays)
        } else {
            monthData
        }
    }
}
