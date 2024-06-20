package com.hobbyloop.feature.reservation.reservation_completion

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.core.ui.componenet.button.FixedBottomButton
import com.hobbyloop.feature.reservation.reservation_completion.component.ReservationCompletionMethodSection
import com.hobbyloop.feature.reservation.reservation_completion.component.ReservationCompletionSectionFooter
import com.hobbyloop.feature.reservation.reservation_completion.component.ReservationCompletionSectionHeader
import com.hobbyloop.feature.reservation.reservation_completion.component.ReservationCompletionUserInfoSection
import com.hobbyloop.feature.reservation.reservation_completion.component.top_bar.ReservationCompletionTopBar
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import theme.HobbyLoopColor

@Composable
internal fun ReservationCompletionScreen(
    viewModel: ReservationCompletionScreenViewModel = hiltViewModel(),
    navigateToReservationHome: () -> Unit
) {
    val state = viewModel.collectAsState().value
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            CompletionSideEffect.NavigateToReservationHome -> navigateToReservationHome()
        }
    }
    
    ReservationCompletionScreen(
        name = state.name,
        phoneName = state.phoneNumber,
        navigateToReservationHome = viewModel::navigateToHomeScreen
    )
}

@Composable
internal fun ReservationCompletionScreen(
    name: String,
    phoneName: String,
    navigateToReservationHome: () -> Unit
) {
    Scaffold(
        topBar = {
            ReservationCompletionTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
            )
        },
        containerColor = Color.White,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ReservationCompletionSectionHeader(name = name)

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(HobbyLoopColor.Gray20)
                )

                ReservationCompletionMethodSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    centerIconImageUrl = "",
                    classTitle = "6:1 체형교정 필라테스",
                    centerName = "필라피티 스튜디오",
                    instructorName = "이민주",
                    classTime = "2023. 5. 12 금 09:00 - 09:50",
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(HobbyLoopColor.Gray20)
                )

                ReservationCompletionUserInfoSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    name = name,
                    phoneNumber = phoneName
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(HobbyLoopColor.Gray20)
                )

                ReservationCompletionSectionFooter(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(113.dp))
            }

            FixedBottomButton(
                isSelected = true,
                onClick = navigateToReservationHome,
                text = "예약하기",
                selectedColor = HobbyLoopColor.Primary,
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
fun PreviewReservationCompletionScreen() {
    ReservationCompletionScreen(
        name = "김지원",
        phoneName = "01012345678",
        navigateToReservationHome = {}
    )
}
