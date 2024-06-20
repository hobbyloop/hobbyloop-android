package com.hobbyloop.core.ui.componenet.yearly_calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.core.ui.componenet.yearly_calendar.component.DaysOfMonth
import com.hobbyloop.core.ui.componenet.yearly_calendar.component.MonthHeader
import com.hobbyloop.data.repository.local.calendar.model.CalendarDay
import com.hobbyloop.data.repository.local.calendar.model.CalendarMonth
import com.hobbyloop.data.repository.local.calendar.model.CalendarYear
import com.hobbyloop.data.repository.local.calendar.model.DaySelected
import com.hobbyloop.data.repository.local.calendar.model.DaySelectedStatus
import com.hobbyloop.core.ui.componenet.yearly_calendar.state.YearlyCalendarIntent
import com.hobbyloop.core.ui.componenet.yearly_calendar.state.YearlyCalendarSideEffect
import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import theme.HobbyLoopColor

@Composable
fun YearlyReservationCalendar(
    classData: List<Pair<Instructor, List<ClassInfo>>>,
    modifier: Modifier = Modifier,
    viewModel: YearlyReservationCalendarViewModel = hiltViewModel(),
    content: @Composable (DaySelected) -> Unit = {}
) {
    val state = viewModel.collectAsState().value
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is YearlyCalendarSideEffect.ShowError -> {
                // TODO 추후 에러 핸들링
            }
        }
    }

    YearlyReservationCalendarView(
        modifier = modifier,
        classData = classData,
        selectedMonth = state.selectedMonth,
        daySelected = state.selectedDay,
        selectedMonthIndex = state.selectedMonthIndex,
        calendarYear = state.calendarYear,
        errorMessage = state.errorMessage,
        isLoading = state.isLoading,
        handleIntent = viewModel::handleIntent,
        content = content
    )
}

@Composable
fun YearlyReservationCalendarView(
    modifier: Modifier,
    classData: List<Pair<Instructor, List<ClassInfo>>>,
    selectedMonth: CalendarMonth,
    daySelected: DaySelected?,
    selectedMonthIndex: Int,
    calendarYear: CalendarYear,
    errorMessage: String?,
    isLoading: Boolean,
    handleIntent: (YearlyCalendarIntent) -> Unit = {},
    content: @Composable (DaySelected) -> Unit = {}
) {
    // classData를 인자로 받아 해당값이 초기화 될 때 해당값을 CurrentMonthCalendarViewModel에서 관리하는 UiState의 dateList 프로퍼티에 업데이트를 한다.
    LaunchedEffect(classData) {
        handleIntent(
            YearlyCalendarIntent.LoadReservations(classInfo = classData)
        )
    }

    Box(
        modifier = modifier
    ) {
        when {
            errorMessage != null -> {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column {
                    MonthHeader(
                        year = selectedMonth.year.toString(),
                        month = selectedMonth.month.toString(),
                        selectedMonthIndex = selectedMonthIndex,
                        endMonthIndex = calendarYear.size,
                        onPreviousMonthClick = {
                            handleIntent(
                                YearlyCalendarIntent.PreviousMonth
                            )
                        },
                        onNextMonthClick = {
                            handleIntent(
                                YearlyCalendarIntent.NextMonth
                            )
                        }
                    )

                    DaysOfMonth(
                        month = selectedMonth,
                        selectedDay = daySelected,
                        onDayClicked = { daySelected ->
                            handleIntent(
                                YearlyCalendarIntent.SelectDay(daySelected = daySelected)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    daySelected?.let {
                        content(it)
                    }
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(color = HobbyLoopColor.Primary, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewYearlyReservationCalendarView() {
    Surface {
        // 더미 데이터 생성
        val instructors = listOf(
            Instructor(name = "Instructor 1", imageUrl = "", history = listOf("History 1")),
            Instructor(name = "Instructor 2", imageUrl = "", history = listOf("History 2"))
        )

        val classInfos1 = listOf(
            ClassInfo(classId = 1, dateTime = "2024-06-15 09:00 - 10:00", title = "Class 1", difficulty = "Intermediate", currentReservationCount = 5, fullReservationCount = 10),
            ClassInfo(classId = 2, dateTime = "2024-06-16 10:00 - 11:00", title = "Class 2", difficulty = "Beginner", currentReservationCount = 3, fullReservationCount = 10)
        )

        val classInfos2 = listOf(
            ClassInfo(classId = 3, dateTime = "2024-07-15 09:00 - 10:00", title = "Class 3", difficulty = "Advanced", currentReservationCount = 7, fullReservationCount = 10),
            ClassInfo(classId = 4, dateTime = "2024-07-16 10:00 - 11:00", title = "Class 4", difficulty = "Beginner", currentReservationCount = 2, fullReservationCount = 10)
        )

        val calendarDays = (1..30).map { day ->
            CalendarDay(day = day, status = DaySelectedStatus.NoSelected)
        }

        val calendarYear = listOf(
            CalendarMonth(name = "June", year = 2024, calendarTypeMonth = 5, calendarDays = calendarDays),
            CalendarMonth(name = "July", year = 2024, calendarTypeMonth = 6, calendarDays = calendarDays)
        )

        val selectedDay = DaySelected(
            year = 2024,
            month = 6,
            day = 15,
            classInfoList = listOf(
                instructors[0] to classInfos1,
                instructors[1] to classInfos2
            )
        )

        YearlyReservationCalendarView(
            modifier = Modifier.fillMaxSize(),
            classData = listOf(
                instructors[0] to classInfos1,
                instructors[1] to classInfos2
            ),
            selectedMonth = calendarYear[0],
            daySelected = selectedDay,
            selectedMonthIndex = 0,
            calendarYear = calendarYear,
            errorMessage = null,
            isLoading = false,
            handleIntent = {}
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 42.dp)
            ) {
                Text(text = "Selected Day: ${it.year}-${it.month}-${it.day}")
            }
        }
    }
}

