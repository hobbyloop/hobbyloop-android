package com.hobbyloop.feature.reservation.ticket_detail

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.Gray60
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.component.button.FixedBottomButton
import com.hobbyloop.feature.reservation.component.top_bar.ReservationDetailTopAppBar
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor
import com.hobbyloop.feature.reservation.ticket_detail.component.ClassContent
import com.hobbyloop.feature.reservation.ticket_detail.component.ClassPager
import com.hobbyloop.feature.reservation.ticket_detail.component.InstructorInfo
import com.hobbyloop.feature.reservation.ticket_detail.component.bottom_sheet.ClassWaitRegistration
import com.hobbyloop.feature.reservation.ticket_detail.state.ReservationDetailState
import com.hobbyloop.feature.reservation.ticket_detail.state.ReservationTicketDetailIntent
import com.hobbyloop.feature.reservation.ticket_detail.state.ReservationTicketDetailSideEffect
import com.hobbyloop.feature.reservation.ticket_detail.yearly_calendar.CalendarView
import kotlinx.coroutines.flow.distinctUntilChanged
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ReservationTicketDetailScreen(
    viewModel: ReservationTicketDetailViewModel = hiltViewModel(),
    onCloseClick: () -> Unit,
    navigateToReservationClassDetail: (classId: String) -> Unit,
) {
    val state = viewModel.collectAsState().value
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ReservationTicketDetailSideEffect.NavigateToReservationClassDetail -> {
                navigateToReservationClassDetail(sideEffect.classId)
            }
            is ReservationTicketDetailSideEffect.ReservationTicketSuccess -> {}
            is ReservationTicketDetailSideEffect.ReservationTicketFailed -> {}
        }
    }

    if (state is ReservationDetailState.Success) {
        ReservationTicketDetailScreen(
            centerName = state.centerName,
            isUpdating = state.isUpdating,
            classInfoList = state.classInfoList,
            selectedClassInfo = state.selectedClassInfo,
            selectedWaitClassInfo = state.selectedWaitClassInfo,
            isInstructorDetailsVisible = state.isInstructorDetailsVisible,
            isReservationBottomSheetOpen = state.isReservationBottomSheetOpen,
            onCloseClick = onCloseClick,
            navigateToReservationClassDetail = viewModel::navigateToReservationClassDetail,
            handleIntent = { viewModel.handleIntent(it) }
        )
    } else {
        // Handle loading and error states
        ReservationTicketDetailScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReservationTicketDetailScreen(
    centerName: String = "",
    isUpdating: Boolean = false,
    classInfoList: List<Pair<Instructor, List<ClassInfo>>> = emptyList(),
    selectedClassInfo: ClassInfo? = null,
    selectedWaitClassInfo: ClassInfo? = null,
    isInstructorDetailsVisible: Boolean = false,
    isReservationBottomSheetOpen: Boolean = false,
    onCloseClick: () -> Unit = { },
    navigateToReservationClassDetail: () -> Unit = { },
    handleIntent: (ReservationTicketDetailIntent) -> Unit = { }
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    // UiState에서 관리하는 isReservationBottomSheetOpen 프로퍼티로 바텀 시트를 열고 닫는 LaunchedEffect
    LaunchedEffect(isReservationBottomSheetOpen) {
        if (isReservationBottomSheetOpen) {
            bottomSheetScaffoldState.bottomSheetState.expand()
        } else {
            bottomSheetScaffoldState.bottomSheetState.hide()
        }
    }

    // 바텀 시트를 드래그 이벤트로 닫을때 상태를 업데이트 하는 LaunchedEffect
    LaunchedEffect(bottomSheetScaffoldState.bottomSheetState) {
        snapshotFlow { bottomSheetScaffoldState.bottomSheetState.currentValue }
            .distinctUntilChanged()
            .collect { currentValue ->
                val isExpanded = currentValue == SheetValue.Expanded
                handleIntent(
                    ReservationTicketDetailIntent.SetReservationBottomSheetOpenTicket(isExpanded)
                )
            }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            ClassWaitRegistration(
                isUpdating = isUpdating,
                onRegisterWaitClick = {
                    selectedWaitClassInfo?.let {
                        ReservationTicketDetailIntent.ReserveClass(
                            classInfo = it
                        )
                    }?.let {
                        handleIntent(it)
                    }
                })
        },
        sheetContainerColor = Color.White,
        sheetContentColor = Color.White,
        sheetPeekHeight = 0.dp
    ) {
        Scaffold(
            topBar = {
                ReservationDetailTopAppBar(
                    title = centerName,
                    onCloseClick = onCloseClick
                )
            },
        ) { padding ->
            val scrollState = rememberScrollState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (classInfoList.isEmpty()) {
                    CircularProgressIndicator(
                        color = Purple,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .verticalScroll(scrollState),
                    ) {
                        CalendarView(
                            classData = classInfoList,
                            onResetInstructorDetailsVisible = {
                                handleIntent(ReservationTicketDetailIntent.ResetInstructorDetailsVisible)
                            }
                        ) { daySelected ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize(animationSpec = tween(durationMillis = 1000))
                            ) {
                                Crossfade(
                                    targetState = daySelected.classInfoList,
                                    label = "ClassChangeAnimation"
                                ) { reservation ->
                                    if (reservation != null) {
                                        ClassPager(
                                            classInfo = reservation,
                                            onResetInstructorDetailsVisible = {
                                                handleIntent(
                                                    ReservationTicketDetailIntent.ResetInstructorDetailsVisible
                                                )
                                            }
                                        ) { instructorWithClasses ->

                                            // 강사 정보 컴포저블
                                            InstructorInfo(
                                                instructorWithClasses = instructorWithClasses,
                                                isInstructorDetailsVisibleState = isInstructorDetailsVisible,
                                                onToggleInstructorDetailsVisible = {
                                                    handleIntent(
                                                        ReservationTicketDetailIntent.ToggleInstructorDetailsVisible
                                                    )
                                                }
                                            )

                                            // 회색선 컴포저블 함수
                                            Spacer(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(16.dp)
                                                    .background(Gray20)
                                            )

                                            // 가능수업 + 수업에 대한 정보를 보여주는 Column 컴포저블
                                            ClassContent(
                                                instructorWithClasses = instructorWithClasses,
                                                selectedClassInfoState = selectedClassInfo,
                                                onExpandBottomSheetClick = {
                                                    handleIntent(
                                                        ReservationTicketDetailIntent.SetReservationBottomSheetOpenTicket(
                                                            true
                                                        )
                                                    )
                                                },
                                                onSelectClassInfo = { classInfo ->
                                                    handleIntent(
                                                        ReservationTicketDetailIntent.SelectClassInfo(
                                                            classInfo
                                                        )
                                                    )
                                                },
                                                onSelectWaitClassInfo = { classInfo ->
                                                    handleIntent(
                                                        ReservationTicketDetailIntent.SelectWaitClassInfo(
                                                            classInfo
                                                        )
                                                    )
                                                }
                                            )

                                            Spacer(modifier = Modifier.height(137.dp))
                                        }
                                    } else {
                                        // 수업이 없는 조건문
                                    }
                                }
                            }
                        }
                    }

                    FixedBottomButton(
                        isSelected = selectedClassInfo != null,
                        onClick = {
                            navigateToReservationClassDetail()
                        },
                        text = "선택완료",
                        selectedColor = Purple,
                        unselectedColor = Gray60,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp)
                            .offset(y = (-25).dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewReservationTicketDetailScreen() {
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
    val dummyClassInfoList = instructors.zip(classes)

    ReservationTicketDetailScreen(
        centerName = "발란스 스튜디오",
        isUpdating = false,
        classInfoList = dummyClassInfoList,
        selectedClassInfo = dummyClassInfoList[0].second[0],
        selectedWaitClassInfo = dummyClassInfoList[1].second[0],
        isInstructorDetailsVisible = false,
        isReservationBottomSheetOpen = false,
        onCloseClick = { },
        navigateToReservationClassDetail = { },
        handleIntent = { }
    )
}

