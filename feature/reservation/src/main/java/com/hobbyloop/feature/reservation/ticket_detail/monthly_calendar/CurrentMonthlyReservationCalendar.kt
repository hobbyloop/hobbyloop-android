package com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.Gray40
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import com.hobbyloop.feature.reservation.ticket_detail.component.lazylist.LazyListUtil.calculateAdjustmentAfterCompletion
import com.hobbyloop.feature.reservation.ticket_detail.component.lazylist.LazyListUtil.calculateViewportCenter
import com.hobbyloop.feature.reservation.ticket_detail.component.lazylist.LazyListUtil.filterFullyVisibleItems
import com.hobbyloop.feature.reservation.ticket_detail.component.lazylist.LazyListUtil.findClosestCenterItem
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.component.MonthlyCalendarItem
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.model.DateInfo
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state.CurrentMonthCalendarIntent
import com.hobbyloop.feature.reservation.ticket_detail.monthly_calendar.state.CurrentMonthCalendarSideEffect
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.model.DaySelected
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CurrentMonthlyReservationCalendar(
    classData: List<Pair<Instructor, List<ClassInfo>>>,
    modifier: Modifier = Modifier,
    viewModel: CurrentMonthCalendarViewModel = hiltViewModel(),
    baseColor: Color = Purple,
    colorMode: ColorMode = ColorMode.POSITION_BASED,
    positionBasedModeLeftBackgroundColor: Color = Gray20,
    positionBasedModeRightBackgroundColor: Color = Gray40,
    positionBasedModeLeftTextColor: Color = Gray40,
    positionBasedModeRightTextColor: Color = Color.Black,
    positionBasedModeCenterTextColor: Color = Color.White,
    content: @Composable (DaySelected) -> Unit = {}
) {
    val state = viewModel.collectAsState().value
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CurrentMonthCalendarSideEffect.ShowError -> {
                // TODO 추후 에러 핸들링
            }
        }
    }

    CurrentMonthlyReservationCalendarView(
        modifier = modifier,
        dateList = state.dateList,
        classData = classData,
        centerScrollOffset = state.centerScrollOffset,
        currentCenterIndex = state.currentCenterIndex,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        baseColor = baseColor,
        colorMode = colorMode,
        positionBasedModeLeftBackgroundColor = positionBasedModeLeftBackgroundColor,
        positionBasedModeRightBackgroundColor = positionBasedModeRightBackgroundColor,
        positionBasedModeLeftTextColor = positionBasedModeLeftTextColor,
        positionBasedModeRightTextColor = positionBasedModeRightTextColor,
        positionBasedModeCenterTextColor = positionBasedModeCenterTextColor,
        handleIntent = { viewModel.handleIntent(it) },
        content = content
    )
}

@Composable
fun CurrentMonthlyReservationCalendarView(
    dateList: List<DateInfo>,
    classData: List<Pair<Instructor, List<ClassInfo>>>,
    centerScrollOffset: Int,
    currentCenterIndex: Int,
    isLoading: Boolean,
    errorMessage: String?,
    modifier: Modifier,
    baseColor: Color = Purple,
    colorMode: ColorMode = ColorMode.POSITION_BASED,
    positionBasedModeLeftBackgroundColor: Color = Gray20,
    positionBasedModeRightBackgroundColor: Color = Gray40,
    positionBasedModeLeftTextColor: Color = Gray40,
    positionBasedModeRightTextColor: Color = Color.Black,
    positionBasedModeCenterTextColor: Color = Color.White,
    handleIntent: (CurrentMonthCalendarIntent) -> Unit = {},
    content: @Composable (DaySelected) -> Unit = {}
) {
    val listState = rememberLazyListState()
    val screenWidthPx = with(LocalDensity.current) { // 디바이스의 화면 너비를 픽셀 단위로 계산하여 저장
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }
    val halfScreen = screenWidthPx / 2f // 현재 디바이스의 절반에 위치하는 픽셀

    // classData를 인자로 받아 해당값이 초기화 될 때 해당값을 CurrentMonthCalendarViewModel에서 관리하는 UiState의 dateList 프로퍼티에 업데이트를 한다.
    LaunchedEffect(classData) {
        handleIntent(
            CurrentMonthCalendarIntent.LoadReservations(classInfo = classData)
        )
    }

    // index의 값이 변경이 될때만 새 index 위치로 애니메이션 스크롤을 트리거함
    LaunchedEffect(centerScrollOffset) {
        listState.animateScrollBy(centerScrollOffset.toFloat())

        // 현재 뷰포트 중앙 위치를 계산
        val viewportCenter = calculateViewportCenter(listState)
        // 뷰포트 중앙에서 가장 가까운 아이템을 찾음
        val closestCenterItem = findClosestCenterItem(listState, viewportCenter)
        // 가장 가까운 아이템이 있을 경우, 해당 아이템의 인덱스를 현재 중앙 인덱스로 설정
        closestCenterItem?.let {
            handleIntent(
                CurrentMonthCalendarIntent.UpdateCurrentCenterIndex(centerIndex = it.index)
            )
        }
    }

    // 화면 진입 후 현재 날짜 데이터로 스크롤 위치로 스크롤을 트리거 함
    LaunchedEffect(Unit) {
        listState.animateScrollToItem(currentCenterIndex)
    }

    listState.apply {
        // 스크롤이 진행중 일 때
        if (isScrollInProgress) {
            // 스크롤이 완료되면 실행되는 코드 블록
            DisposableEffect(Unit) {
                onDispose {
                    // 화면에 완전히 보이는 아이템들을 필터링
                    val fullyVisibleItems = filterFullyVisibleItems(listState)
                    // 필터링된 아이템을 기반으로 스크롤 조정 필요한 오프셋을 계산
                    val adjustment = calculateAdjustmentAfterCompletion(listState, dateList, fullyVisibleItems)
                    // 계산된 오프셋 값을 사용하여 외부에서 centerScrollOffset 업데이트
                    handleIntent(
                        CurrentMonthCalendarIntent.UpdateCenterScrollOffset(adjustment.toInt())
                    )
                }
            }
        }
    }

    // 화면 진입 후 현재 날짜 데이터로 스크롤 위치로 스크롤을 트리거 함
    LaunchedEffect(Unit) {
        delay(50)
        listState.animateScrollToItem(currentCenterIndex)
    }

    Box(modifier = modifier) {
        when {
            errorMessage != null -> {
                Text(text = errorMessage, color = Color.Red, modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "")
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.dot_ic),
                        contentDescription = "Reservation",
                        modifier = Modifier
                            .size(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    LazyRow(state = listState) {
                        itemsIndexed(
                            items = dateList,
                            contentType = { _, _ -> "data" }  // index와 item이 사용되지 않으므로 '_'로 대체
                        ) { index: Int, dateInfo: DateInfo ->
                            // LazyRow의 현재 레이아웃 정보를 사용해 현재 아이템의 상세 정보를 가져옴
                            val layoutInfo = listState.layoutInfo
                            val itemInfo = layoutInfo.visibleItemsInfo.find { it.index == index }

                            // 현재 아이템의 중심 위치를 계산
                            val itemCenter = itemInfo?.let { it.offset + it.size / 2f } ?: 0f
                            val distanceToHalfScreen = Math.abs(itemCenter - halfScreen)

                            // 현재 아이템의 중앙 위치와 화면 중앙 사이의 거리를 계산
                            val centerDistance = Math.abs(itemCenter - halfScreen)

                            val (backgroundColor, textColor) = when (colorMode) {
                                ColorMode.PROXIMITY_BASED -> {
                                    val backgroundColor = calculateColorByProximityToCenter(
                                        color = baseColor,
                                        centerDistance = centerDistance,
                                        halfScreen = halfScreen
                                    )
                                    val textColor = calculateColorByProximityToCenter(
                                        color = if (centerDistance / halfScreen < 0.15) Color.White else Color.Black,
                                        centerDistance = centerDistance,
                                        halfScreen = halfScreen
                                    )
                                    Pair(backgroundColor, textColor)
                                }
                                ColorMode.POSITION_BASED -> {
                                    val backgroundColor = when {
                                        distanceToHalfScreen <= 60f -> baseColor
                                        itemCenter < halfScreen -> positionBasedModeLeftBackgroundColor
                                        itemCenter > halfScreen -> positionBasedModeRightBackgroundColor
                                        else -> Color.Transparent
                                    }
                                    val textColor = when {
                                        distanceToHalfScreen <= 60f -> positionBasedModeCenterTextColor
                                        itemCenter < halfScreen -> positionBasedModeLeftTextColor
                                        itemCenter > halfScreen -> positionBasedModeRightTextColor
                                        else -> Color.Transparent
                                    }
                                    Pair(backgroundColor, textColor)
                                }
                            }

                            // MonthlyCalendarItem 컴포저블 호출
                            MonthlyCalendarItem(
                                dateInfo = dateInfo,
                                backgroundColor = backgroundColor,
                                textColor = textColor,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    dateList.getOrNull(currentCenterIndex + 2)?.let { dateInfo ->
                        val daySelected = DaySelected(
                            year = dateInfo.year,
                            month = dateInfo.month,
                            day = dateInfo.day,
                            classInfoList = dateInfo.classInfoList ?: emptyList()
                        )
                        Box {
                            content(daySelected)

                            if (isLoading) {
                                CircularProgressIndicator(color = Purple, modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCurrentMonthlyReservationCalendarView() {
    Surface {
        CurrentMonthlyReservationCalendarView(
            modifier = Modifier,
            dateList = listOf(
                DateInfo(2024, 6, 15, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-15T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 16, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-16T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 17, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-17T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 18, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-18T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 19, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-19T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 20, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-20T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 21, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-21T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 22, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-22T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 23, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-23T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 24, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-24T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 25, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-25T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 26, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-26T10:00:00", "Class 2", "Beginner", 3, 10)))),
            ),
            classData = emptyList(),
            centerScrollOffset = 0,
            currentCenterIndex = 0,
            isLoading = false,
            errorMessage = null,
            baseColor = Purple,
            colorMode = ColorMode.POSITION_BASED,
            positionBasedModeLeftBackgroundColor = Gray20,
            positionBasedModeRightBackgroundColor = Gray40,
            positionBasedModeLeftTextColor = Gray40,
            positionBasedModeRightTextColor = Color.Black,
            positionBasedModeCenterTextColor = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewCurrentMonthlyReservationCalendarViewLoading() {
    Surface {
        CurrentMonthlyReservationCalendarView(
            modifier = Modifier,
            dateList = listOf(
                DateInfo(2024, 6, 15, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-15T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 16, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-16T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 17, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-17T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 18, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-18T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 19, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-19T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 20, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-20T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 21, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-21T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 22, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-22T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 23, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-23T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 24, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-24T10:00:00", "Class 2", "Beginner", 3, 10)))),
                DateInfo(2024, 6, 25, "Mon", listOf(Instructor("Instructor 1", "", emptyList()) to listOf(ClassInfo(1, "2024-06-25T09:00:00", "Class 1", "Intermediate", 5, 10)))),
                DateInfo(2024, 6, 26, "Tue", listOf(Instructor("Instructor 2", "", emptyList()) to listOf(ClassInfo(2, "2024-06-26T10:00:00", "Class 2", "Beginner", 3, 10)))),
            ),
            classData = emptyList(),
            centerScrollOffset = 0,
            currentCenterIndex = 0,
            isLoading = true,
            errorMessage = null,
            baseColor = Purple,
            colorMode = ColorMode.POSITION_BASED,
            positionBasedModeLeftBackgroundColor = Gray20,
            positionBasedModeRightBackgroundColor = Gray40,
            positionBasedModeLeftTextColor = Gray40,
            positionBasedModeRightTextColor = Color.Black,
            positionBasedModeCenterTextColor = Color.White
        )
    }
}

/**
 * 주어진 거리에 따라 아이템의 색상을 계산
 * 중앙에서의 거리 비율에 따라 색상의 투명도를 조절하여,
 * 아이템이 중앙에 가까울수록 더 높은 투명도를 갖도록 함
 *
 * @param color 기본 색상
 * @param centerDistance 아이템 중심까지의 거리
 * @param halfScreen 화면의 절반 크기
 * @return 조정된 색상
 */
fun calculateColorByProximityToCenter(
    color: Color,
    centerDistance: Float,
    halfScreen: Float
): Color {
    val distanceRatio = centerDistance / halfScreen
    return when {
        distanceRatio < 0.18 -> color  // 가장 가까운 위치
        distanceRatio < 0.5 -> color.copy(alpha = 0.45f)
        distanceRatio < 0.85 -> color.copy(alpha = 0.25f)
        else -> color.copy(alpha = 0.1f)  // 가장 먼 위치
    }
}

enum class ColorMode {
    /**
     * [PROXIMITY_BASED 모드]
     * 아이템의 중앙에서의 거리 비율에 따라 색상의 투명도를 조절하여 색상을 적용
     * 아이템이 중앙에 가까울수록 더 높은 불투명도를 갖음
     */
    PROXIMITY_BASED,

    /**
     * [POSITION_BASED 모드]
     * 아이템의 중앙 위치를 기준으로 왼쪽과 오른쪽에 따라 색상을 적용
     * 중앙 기준 왼쪽에 위치한 아이템은 Gray, 오른쪽에 위치한 아이템은 다른 색상으로 표시
     */
    POSITION_BASED
}
