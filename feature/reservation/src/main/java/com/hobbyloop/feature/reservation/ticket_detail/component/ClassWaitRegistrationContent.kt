package com.hobbyloop.feature.reservation.ticket_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.ui.R
import com.hobbyloop.core.ui.componenet.button.FixedBottomButton
import theme.HobbyLoopColor

@Composable
fun ClassWaitRegistrationContent(
    isUpdating: Boolean,
    onRegisterWaitClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(Color.White)
            .padding(top = 9.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_reservation_color_42),
                contentDescription = "대기가능 모래시계 아이콘",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "대기 신청을 할까요?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = buildAnnotatedString {
                    append("내 대기순서 ")
                    withStyle(style = SpanStyle(color = HobbyLoopColor.Primary)) {
                        append("5")
                    }
                    append("번째")
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "예약 가능한 상태가 되면 앱 알림으로 알려드릴게요!",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight(500)
            )
        }

        FixedBottomButton(
            isSelected = true,
            text = "대기신청",
            selectedColor = HobbyLoopColor.Primary,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .offset(y = (-25).dp),
            onClick = {
                onRegisterWaitClick()
            },
        )

        if (isUpdating) {
            CircularProgressIndicator(
                color = HobbyLoopColor.Primary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
