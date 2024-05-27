package com.hobbyloop.feature.reservation.center_detail

import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.center_detail.component.ClassContent
import com.hobbyloop.feature.reservation.center_detail.component.ClassPager
import com.hobbyloop.feature.reservation.center_detail.component.InstructorInfo
import com.hobbyloop.feature.reservation.center_detail.component.bottom_sheet.ClassWaitRegistration
import com.hobbyloop.feature.reservation.center_detail.component.button.FixedBottomButton
import com.hobbyloop.feature.reservation.center_detail.component.top_bar.ReservationDetailTopAppBar
import com.hobbyloop.feature.reservation.center_detail.state.ReservationDetailIntent
import com.hobbyloop.feature.reservation.center_detail.state.ReservationDetailState
import com.hobbyloop.feature.reservation.center_detail.yearly_calendar.CalendarView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReservationCenterDetailScreen(
    viewModel: ReservationCenterDetailViewModel = hiltViewModel(),
    backgroundColor: Color,
    onCloseClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            val selectedWaitClassInfo = if (uiState is ReservationDetailState.Success) {
                (uiState as ReservationDetailState.Success).selectedWaitClassInfo
            } else {
                null
            }

            val isUpdating = if (uiState is ReservationDetailState.Success) {
                (uiState as ReservationDetailState.Success).isUpdating
            } else {
                false
            }

            ClassWaitRegistration(
                isUpdating = isUpdating,
                onRegisterWaitClick = {
                    selectedWaitClassInfo?.let { ReservationDetailIntent.ReserveClass(classInfo = it) }
                        ?.let { viewModel.handleIntent(intent = it) }
                })
        },
        sheetContainerColor = Color.White,
        sheetContentColor = Color.White,
        sheetPeekHeight = 0.dp
    ) {
        Scaffold(
            topBar = {
                ReservationDetailTopAppBar(
                    title = "발란스 스튜디오", // 하드코딩된 값이라 추후 값 교체해야함
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
                when (uiState) {
                    is ReservationDetailState.Loading -> {
                        CircularProgressIndicator(
                            color = Purple,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is ReservationDetailState.Error -> {
                        Text(
                            text = (uiState as ReservationDetailState.Error).errorMessage,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is ReservationDetailState.Success -> {
                        val successState = uiState as ReservationDetailState.Success

                        LaunchedEffect(successState.isReservationBottomSheetOpen) {
                            Log.d("BottomSheet", "${successState.isReservationBottomSheetOpen}")
                            if (successState.isReservationBottomSheetOpen) {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.hide()
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .verticalScroll(scrollState),
                        ) {
                            CalendarView(
                                classData = successState.classInfoList,
                                onResetInstructorDetailsVisible = {
                                    viewModel.handleIntent(ReservationDetailIntent.ResetInstructorDetailsVisible)
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
                                                    viewModel.handleIntent(ReservationDetailIntent.ResetInstructorDetailsVisible)
                                                }
                                            ) { instructorWithClasses ->

                                                // 강사 정보 컴포저블
                                                InstructorInfo(
                                                    instructorWithClasses = instructorWithClasses,
                                                    isInstructorDetailsVisibleState = successState.isInstructorDetailsVisible,
                                                    onToggleInstructorDetailsVisible = {
                                                        viewModel.handleIntent(
                                                            ReservationDetailIntent.ToggleInstructorDetailsVisible
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
                                                    selectedClassInfoState = successState.selectedClassInfo,
                                                    onExpandBottomSheetClick = {
                                                        viewModel.handleIntent(
                                                            ReservationDetailIntent.SetReservationBottomSheetOpen(
                                                                isOpen = true
                                                            )
                                                        )
                                                    },
                                                    onSelectClassInfo = { classInfo ->
                                                        viewModel.handleIntent(
                                                            ReservationDetailIntent.SelectClassInfo(
                                                                classInfo = classInfo
                                                            )
                                                        )
                                                    },
                                                    onSelectWaitClassInfo = { classInfo ->
                                                        viewModel.handleIntent(
                                                            ReservationDetailIntent.SelectWaitClassInfo(
                                                                classInfo = classInfo
                                                            )
                                                        )
                                                    }
                                                )

                                                Spacer(modifier = Modifier.height(137.dp))
                                            }
                                        } else {
                                            Column {
                                                for (i in 1..10) {
                                                    Text(text = "수강권 없음", fontSize = 20.sp)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        FixedBottomButton(
                            isSelected = (uiState as ReservationDetailState.Success).selectedClassInfo == null,
                            onClick = {
                                // TODO: 선택 완료 클릭 후 다음 화면 넘어가도록 구현해야함
                            },
                            text = "선택완료",
                            selectedColor = Color.Gray,
                            unselectedColor = Purple,
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
}
