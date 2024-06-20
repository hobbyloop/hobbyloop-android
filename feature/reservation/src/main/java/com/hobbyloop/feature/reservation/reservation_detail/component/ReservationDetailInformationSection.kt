package com.hobbyloop.feature.reservation.reservation_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.core.ui.componenet.reservation.section.SectionHeader
import com.hobbyloop.core.ui.componenet.textfield.CustomizableTextField
import com.hobbyloop.core.ui.componenet.textfield.TextFieldStyle
import com.hobbyloop.core.ui.util.TextFieldUtil
import com.hobbyloop.ui.R
import theme.HobbyLoopColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClassDetailReservationInformation(
    name: String,
    phoneNumber: String,
    isNameValid: Boolean,
    isPhoneNumberValid: Boolean,
    onNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
) {
        Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionHeader(
                title = "예약자 정보",
                iconRes = R.drawable.ic_user_color,
                isIconTintEnabled = true,
                iconTint = HobbyLoopColor.Primary,
                iconDescription = "공지사항 아이콘",
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "정보는 마이페이지에서 수정 가능해요.",
                style = TextStyle(
                    color = HobbyLoopColor.Gray60,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                )
            )
        }

        Text(
            text = "이름",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        CustomizableTextField(
            modifier = Modifier
                .fillMaxWidth(),
            textFieldStyle = TextFieldStyle.KOREAN_NAME_STYLE,
            value = name,
            isValid = isNameValid,
            onValueChange = { newValue ->
                onNameChange(newValue)
            },
            visualTransformation = TextFieldUtil.KoreanNameVisualTransformation(),
            hintMessage = "이름을 입력하세요.",
            errorMessage = "이름 형식이 옳지 않습니다.",
        )

        Text(
            text = "전화번호",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        CustomizableTextField(
            modifier = Modifier
                .fillMaxWidth(),
            textFieldStyle = TextFieldStyle.PHONE_NUMBER_STYLE,
            value = phoneNumber,
            isValid = isPhoneNumberValid,
            onValueChange = { newValue ->
                onPhoneNumberChange(newValue)
            },
            hintMessage = "핸드폰 번호를 입력하세요.",
            visualTransformation = TextFieldUtil.PhoneNumberVisualTransformation(),
            errorMessage = "전화번호 형식이 옳지 않습니다.",
        )
    }
}

@Preview
@Composable
fun PreviewClassDetailReservationInformation() {
    Surface {
        ClassDetailReservationInformation(
            name = "",
            phoneNumber = "",
            isNameValid = false,
            isPhoneNumberValid = false,
            onNameChange = { },
            onPhoneNumberChange = { }
        )
    }
}
