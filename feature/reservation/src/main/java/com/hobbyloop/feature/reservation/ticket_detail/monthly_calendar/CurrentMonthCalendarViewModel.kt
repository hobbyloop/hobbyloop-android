package com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar

import androidx.lifecycle.ViewModel
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.model.DateInfo
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state.CurrentMonthCalendarEvent
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state.CurrentMonthCalendarIntent
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state.CurrentMonthCalendarSideEffect
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state.CurrentMonthCalendarState
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.util.MonthlyCalendarUtils
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.repository.DatesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

const val bufferCount = 4

@HiltViewModel
class CurrentMonthCalendarViewModel @Inject constructor(
    private val datesRepository: DatesRepository
) : ViewModel(), ContainerHost<CurrentMonthCalendarState, CurrentMonthCalendarSideEffect> {

    override val container: Container<CurrentMonthCalendarState, CurrentMonthCalendarSideEffect> = container(
        initialState = CurrentMonthCalendarState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                //TODO 추후 에러 핸들링
                intent { postSideEffect(CurrentMonthCalendarSideEffect.ShowError(throwable.message ?: "Unknown error")) }
            }
        }
    )

    init {
        handleIntent(CurrentMonthCalendarIntent.LoadDates)
    }

    fun handleIntent(intent: CurrentMonthCalendarIntent) {
        when (intent) {
            is CurrentMonthCalendarIntent.LoadDates -> loadDates()
            is CurrentMonthCalendarIntent.LoadReservations -> loadReservations(intent.classInfo)
            is CurrentMonthCalendarIntent.UpdateCenterScrollOffset -> updateCenterScrollOffset(intent.centerScrollOffset)
            is CurrentMonthCalendarIntent.UpdateCurrentCenterIndex -> updateCurrentCenterIndex(intent.centerIndex)
            is CurrentMonthCalendarIntent.UpdateCurrentCenterIndexWithDates -> updateCenterIndexWithDates(intent.dates)
        }
    }

    private fun handleEvent(event: CurrentMonthCalendarEvent) = intent {
        when (event) {
            is CurrentMonthCalendarEvent.DatesLoaded -> {
                reduce {
                    state.copy(dateList = event.dateList, isLoading = false, errorMessage = null)
                }
            }
            is CurrentMonthCalendarEvent.ReservationsLoaded -> {
                reduce {
                    state.copy(dateList = event.dateList, isLoading = false, errorMessage = null)
                }
            }
            is CurrentMonthCalendarEvent.LoadFailed -> {
                reduce {
                    state.copy(isLoading = false, errorMessage = event.error)
                }
            }
        }
    }

    /**
     * 현재 달의 데이터를 가져와 UiState의 dateList 프로퍼티를 업데이트하고, [handleEvent(CurrentMonthCalendarEvent.DatesLoaded(dates))]
     * 업데이트가 완료된 후 현재 달의 데이터를 기반으로 현재 날짜의 인덱스를 추출하여 UiState의 currentCenterIndex 프로퍼티를 업데이트하는 함수. [handleIntent(CurrentMonthCalendarIntent.UpdateCurrentCenterIndexWithDates(dates))]
     *
     * 현재 달의 데이터를 가져와 UiState의 dateList 프로퍼티를 업데이트한 후 순차적으로,
     * 현재 달의 데이터를 기반으로 현재 날짜의 인덱스를 추출하여 UiState의 currentCenterIndex 프로퍼티를 업데이트 해야 하므로
     * blockingIntent{}를 사용하였음.
     *
     * 이 함수는 화면이 표시된 직후(init 블록) 호출되며, 가장 먼저 현재 달의 데이터를 가져와야 하는 로직임.
     */
    private fun loadDates() = blockingIntent {
        try {
            val dates = prepareDateListWithBuffers(bufferCount)
            handleEvent(CurrentMonthCalendarEvent.DatesLoaded(dates)) // 현재 달의 데이터를 가져와 UiState의 dateList 프로퍼티를 업데이트
            handleIntent(CurrentMonthCalendarIntent.UpdateCurrentCenterIndexWithDates(dates)) // UiState의 dateList 프로퍼티 업데이트가 완료된 후 순차적으로, 현재 달의 데이터를 기반으로 현재 날짜의 인덱스를 추출하여 UiState의 currentCenterIndex 프로퍼티를 업데이트
        } catch (e: Exception) {
            handleEvent(CurrentMonthCalendarEvent.LoadFailed(e.message ?: "Unknown error"))
        }
    }

    /**
     * 받아온 수업 정보를 이용하여 현재 달의 데이터인 dateList 프로퍼티의 각각의 DateInfo와 날짜를 비교해 매칭되는 경우, 해당 DateInfo의 classInfoList 속성을 업데이트하여 달력에 수업 정보를 반영하는 함수
     * - 쉽게 말해 수업 정보를 기반으로 현재 달의 날짜와 비교하여 일치하는 경우, 해당 날짜에 수업 정보를 적재하여 달력에 수업 정보를 매칭함
     * - 따라서 호출 시점은 수업 정보가 들어왔을 때이며, LaunchedEffect로 실행하도록 함
     */
    private fun loadReservations(classInfo: List<Pair<Instructor, List<ClassInfo>>>) = intent {
        reduce { // 로딩 상태 확인을 위한 코드, 추후 삭제해야함
            state.copy(isLoading = true, errorMessage = null)
        }
        delay(2000) // 로딩 상태 확인을 위한 코드, 추후 삭제해야함

        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            val updatedDateList = state.dateList.map { dateInfo ->
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

    /**
     * 현재 화면의 정중앙과 가장 가까운 아이템의 중앙 위치를 화면의 정중앙에 맞추기 위한 오프셋 값을 파라미터로 받아 UiState의 centerScrollOffset 속성을 업데이트하는 함수
     * - centerScrollOffset 속성은 화면의 정중앙을 위치시키기 위한 오프셋 값으로, 이 값은 현재 화면의 정중앙과 가장 가까운 아이템의 중앙 위치를 화면의 정중앙에 맞추기 위한 값임
     * - 이 함수는 스크롤이 진행되고 완료되었을 때 호출되어야 하므로, if (isScrollInProgress) -> DisposableEffect(Unit) { onDispose {} } 에서 호출됨
     */
    private fun updateCenterScrollOffset(centerScrollOffset: Int) = intent {
        reduce {
            state.copy(centerScrollOffset = centerScrollOffset)
        }
    }

    /**
     * 화면의 정가운데에 위치한 인덱스를 받아 UiState의 currentCenterIndex 프로퍼티를 업데이트 하는 함수
     * - currentCenterIndex 프로퍼티는 다른 화면으로 이동 후 돌아왔을 때 이전에 선택된 중앙 값을 유지하여 화면을 위치시키기 위한 프로퍼티임
     * - 이 함수는 스크롤이 발생할 때마다 호출되어야 하므로, UiState의 centerScrollOffset를 LaunchedEffect의 인자로 사용하여 해당 블록 내부에서 호출됨
     */
    private fun updateCurrentCenterIndex(centerIndex: Int) = intent {
        reduce {
            val validCenterIndex = if (centerIndex - bufferCount + 1 >= 0) centerIndex - bufferCount + 1 else 0
            state.copy(currentCenterIndex = validCenterIndex)
        }
    }

    /**
     * 화면에 처음 진입했을 때, 현재 달의 데이터(리스트)를 파라미터로 받아 현재 날짜를 화면의 정중앙에 위치시키기 위한 함수
     * - 주어진 날짜 목록에서 오늘 날짜의 인덱스를 찾아 반환하는 함수를 이용해 UiState의 currentCenterIndex 프로퍼티를 업데이트함
     * - UiState의 currentCenterIndex 속성을 기반으로 화면의 정중앙에 날짜를 위치시킴
     * - 이 함수는 화면에 처음 진입하여 현재 달의 데이터를 로드한 직후에 호출되어 화면의 중앙을 맞추도록 함
     */
    private fun updateCenterIndexWithDates(dates: List<DateInfo>) = blockingIntent {
        reduce {
            val index = MonthlyCalendarUtils.findIndexOfTodayInList(dates) - 2
            val validIndex = if (index >= 0) index else 0
            state.copy(currentCenterIndex = validIndex)
        }
    }

    /**
     * 현재 달의 데이터를 가져와 1일과 말일에 버퍼 데이터를 추가하여 UI를 구성하는 함수
     * - 요구된 UI는 1일과 말일이 중앙에 위치하도록 하는 것임
     * - 이를 위해 inputBufferCount는 4로 고정되어 있으며, 앞뒤로 버퍼 데이터를 생성함
     */
    private fun prepareDateListWithBuffers(inputBufferCount: Int): List<DateInfo> {
        val dataList = datesRepository.datesOfCurrentMonth
        val bufferDateInfo = DateInfo(year = -1, month = -1, day = -1, dayOfWeek = "")

        val bufferList = List(inputBufferCount) { bufferDateInfo }

        return bufferList + dataList + bufferList
    }
}
