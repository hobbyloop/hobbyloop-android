package com.hobbyloop.feature.reservation.center_detail.monthly_calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.feature.reservation.center_detail.model.ClassInfo
import com.hobbyloop.feature.reservation.center_detail.model.Instructor
import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.model.DateInfo
import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.state.CurrentMonthCalendarEvent
import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.state.CurrentMonthCalendarIntent
import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.state.CurrentMonthCalendarState
import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.util.MonthlyCalendarUtils
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.repository.DatesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

const val bufferCount = 4

@HiltViewModel
class CurrentMonthCalendarViewModel @Inject constructor(
    private val datesRepository: DatesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurrentMonthCalendarState())
    val uiState: StateFlow<CurrentMonthCalendarState> = _uiState.asStateFlow()

    init {
        handleIntent(CurrentMonthCalendarIntent.LoadDates)
    }

    fun handleIntent(intent: CurrentMonthCalendarIntent) {
        when (intent) {
            is CurrentMonthCalendarIntent.LoadDates -> loadDates()
            is CurrentMonthCalendarIntent.LoadReservations -> loadReservations(intent.classInfo)
            is CurrentMonthCalendarIntent.UpdateCenterScrollOffset -> updateCenterScrollOffset(intent.offset)
            is CurrentMonthCalendarIntent.UpdateCurrentCenterIndex -> updateCurrentCenterIndex(intent.index)
            is CurrentMonthCalendarIntent.UpdateCurrentCenterIndexWithDates -> updateCenterIndexWithDates(intent.dates)
        }
    }

    private fun handleEvent(event: CurrentMonthCalendarEvent) {
        viewModelScope.launch {
            when (event) {
                is CurrentMonthCalendarEvent.DatesLoaded -> {
                    _uiState.update {
                        it.copy(dateList = event.dateList, isLoading = false, errorMessage = null)
                    }
                }
                is CurrentMonthCalendarEvent.ReservationsLoaded -> {
                    delay(2000) // 로딩 상태를 확인하기 위한 딜레이, 추후 삭제해야함
                    _uiState.update {
                        it.copy(dateList = event.dateList, isLoading = false, errorMessage = null)
                    }
                }
                is CurrentMonthCalendarEvent.LoadFailed -> {
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = event.error)
                    }
                }
            }
        }
    }

    private fun loadDates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val dates = prepareDateListWithBuffers(bufferCount)
                handleEvent(CurrentMonthCalendarEvent.DatesLoaded(dates))
                handleIntent(CurrentMonthCalendarIntent.UpdateCurrentCenterIndexWithDates(dates))
            } catch (e: Exception) {
                handleEvent(CurrentMonthCalendarEvent.LoadFailed(e.message ?: "Unknown error"))
            }
        }
    }

    private fun loadReservations(classInfo: List<Pair<Instructor, List<ClassInfo>>>) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")

                val updatedDateList = _uiState.value.dateList.map { dateInfo ->
                    val calendar = Calendar.getInstance()
                    calendar.set(dateInfo.year, dateInfo.month - 1, dateInfo.day)

                    val dayDate = calendar.time

                    val selectedReservations = mutableListOf<Pair<Instructor, List<ClassInfo>>>()

                    classInfo.forEach { (instructor, classes) ->
                        val matchingClasses = classes.filter { classInfo ->
                            val classDate = dateFormat.parse(classInfo.dateTime.substring(0, 10))
                            classDate?.let { dateFormat.format(it) } == dateFormat.format(dayDate)
                        }
                        if (matchingClasses.isNotEmpty()) {
                            selectedReservations.add(Pair(instructor, matchingClasses))
                        }
                    }

                    dateInfo.copy(classInfoList = selectedReservations.ifEmpty { null })
                }
                handleEvent(CurrentMonthCalendarEvent.ReservationsLoaded(updatedDateList))
            } catch (e: Exception) {
                handleEvent(CurrentMonthCalendarEvent.LoadFailed(e.message ?: "Unknown error"))
            }
        }
    }

    private fun updateCenterScrollOffset(offset: Int) {
        _uiState.update {
            it.copy(centerScrollOffset = offset)
        }
    }

    private fun updateCurrentCenterIndex(index: Int) {
        _uiState.update {
            it.copy(currentCenterIndex = index - bufferCount + 1)
        }
    }

    private fun updateCenterIndexWithDates(dates: List<DateInfo>) {
        val index = MonthlyCalendarUtils.findIndexOfTodayInList(dates) - 2
        _uiState.update {
            it.copy(currentCenterIndex = index)
        }
    }

    private fun prepareDateListWithBuffers(inputBufferCount: Int): List<DateInfo> {
        val dataList = datesRepository.datesOfCurrentMonth
        val bufferDateInfo = DateInfo(year = -1, month = -1, day = -1, dayOfWeek = "")

        val bufferList = List(inputBufferCount) { bufferDateInfo }

        return bufferList + dataList + bufferList
    }
}
