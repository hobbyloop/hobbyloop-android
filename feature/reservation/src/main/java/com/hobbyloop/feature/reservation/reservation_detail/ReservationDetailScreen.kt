package com.hobbyloop.feature.reservation.reservation_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.reservation_detail.component.ClassDetailReservationInformation
import com.hobbyloop.feature.reservation.reservation_detail.component.ClassDetailReservationMethod
import com.hobbyloop.feature.reservation.component.ticket.TicketCard
import com.hobbyloop.feature.reservation.reservation_detail.component.ClassNoticeSection
import com.hobbyloop.feature.reservation.component.button.FixedBottomButton
import com.hobbyloop.feature.reservation.component.top_bar.ReservationDetailTopAppBar
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ReservationDetailScreen(
    viewModel: ReservationClassDetailScreenViewModel = hiltViewModel(),
    onCloseClick: () -> Unit,
    navigateToReservationCompletion: (classId: String) -> Unit,
) {
    val state = viewModel.collectAsState().value
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ClassDetailSideEffect.NavigateToReservationCompletion -> navigateToReservationCompletion(sideEffect.classId)
        }
    }

    ReservationDetailScreen(
        name = state.name,
        phoneNumber = state.phoneNumber,
        centerIconImageUrl = state.centerImageUrl,
        classTitle = state.classTitle,
        centerName = state.centerName,
        instructorName = state.instructorName,
        classTime = state.classTime,
        notice = state.notice,
        centerImageUrl = state.centerImageUrl,
        sessionCount = state.sessionCount,
        usagePeriod = state.usagePeriod,
        isNameValid = state.isNameValid,
        isPhoneNumberValid = state.isPhoneNumberValid,
        onNameChange = viewModel::onNameChange,
        onPhoneNumberChange = viewModel::onPhoneNumberChange,
        onCloseClick = onCloseClick,
        navigateToReservationCompletion = viewModel::navigateToReservationCompletion
    )
}

@Composable
internal fun ReservationDetailScreen(
    name: String,
    phoneNumber: String,
    centerIconImageUrl: String,
    classTitle: String,
    centerName: String,
    instructorName: String,
    classTime: String,
    notice: String,
    centerImageUrl: String,
    sessionCount: Int = -1,
    usagePeriod: String,
    isNameValid: Boolean,
    isPhoneNumberValid: Boolean,
    onNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    navigateToReservationCompletion: () -> Unit,
) {
    Scaffold(
        topBar = {
            ReservationDetailTopAppBar(
                title = "수업 예약",
                onCloseClick = onCloseClick
            )
        },
        containerColor = Color.White,
    ) { padding ->
        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                TicketCard(
                    centerIconImageUrl = centerIconImageUrl,
                    classTitle = classTitle,
                    centerName = centerName,
                    instructorName = instructorName,
                    classTime = classTime,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .padding(horizontal = 16.dp),
                )

                Spacer(modifier = Modifier.height(33.dp))

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Gray20)
                )

                ClassNoticeSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(vertical = 24.dp)
                        .padding(horizontal = 16.dp),
                    notice = notice
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Gray20)
                )

                Spacer(modifier = Modifier.height(24.dp))

                ClassDetailReservationMethod(
                    centerImageUrl = centerImageUrl,
                    sessionCount = sessionCount,
                    usagePeriod = usagePeriod
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Gray20)
                )

                ClassDetailReservationInformation(
                    name = name,
                    phoneNumber = phoneNumber,
                    isNameValid = isNameValid,
                    isPhoneNumberValid = isPhoneNumberValid,
                    onNameChange = onNameChange,
                    onPhoneNumberChange = onPhoneNumberChange
                )

                Spacer(modifier = Modifier.height(113.dp))
            }

            FixedBottomButton(
                isSelected = isNameValid && isPhoneNumberValid && name.isNotEmpty() && phoneNumber.isNotEmpty(),
                onClick = { navigateToReservationCompletion() },
                text = "예약하기",
                selectedColor = Purple,
                unselectedColor = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .offset(y = (-10).dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview
@Composable
fun PreviewReservationDetailScreen() {
    Surface {
        ReservationDetailScreen(
            name = "홍길동",
            phoneNumber = "01012345678",
            centerIconImageUrl = "https://example.com/icon.png",
            classTitle = "요가 클래스",
            centerName = "헬스 센터",
            instructorName = "김트레이너",
            classTime = "2023-06-11 10:00",
            notice = "클래스 시작 10분 전에 도착해주세요.",
            centerImageUrl = "https://example.com/image.png",
            sessionCount = 10,
            usagePeriod = "2023-06-11 ~ 2023-07-11",
            isNameValid = true,
            isPhoneNumberValid = true,
            onNameChange = {},
            onPhoneNumberChange = {},
            onCloseClick = {},
            navigateToReservationCompletion = {}
        )
    }
}
