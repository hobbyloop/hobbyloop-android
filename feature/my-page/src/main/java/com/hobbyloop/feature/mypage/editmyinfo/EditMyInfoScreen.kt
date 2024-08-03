package com.hobbyloop.feature.mypage.editmyinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.ActiveStateButton
import com.hobbyloop.core.ui.componenet.inputField.InputInfoContent
import com.hobbyloop.core.ui.selection.DateSelection
import com.hobbyloop.core.ui.componenet.inputField.PhoneNumberVerificationForm
import com.hobbyloop.ui.R
import theme.HobbyLoopTypo
import theme.HobbyLoopColor

@Composable
fun EditMyInfoScreen(
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFF9F9F9))
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    "내 정보 수정",
                    style = HobbyLoopTypo.head16,
                    modifier = Modifier.align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onCloseClick()
                        }
                        .align(Alignment.CenterStart)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            ProfileImage()

            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                InputInfoContent(
                    value = "",
                    headerText = stringResource(id = R.string.signup_name),
                    hintText = stringResource(id = R.string.signup_name_hint),
                    errorText = stringResource(R.string.signup_name_error),
                    isValid = false,
                    valueChange = {

                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                InputInfoContent(
                    value = "",
                    headerText = stringResource(R.string.signup_nickname),
                    hintText = stringResource(R.string.signup_nickname_hint),
                    errorText = stringResource(R.string.signup_nickname_error),
                    isValid = false,
                    valueChange = {

                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                DateSelection(
                    "123",
                    showSheet = {

                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                PhoneNumberVerificationForm(
                    phoneNumber = "000-0000-0000",
                    isPhoneNumberValid = true,
                    code = "code",
                    isVerificationCodeValid = true,
                    showProgress = true,
                    isResendAvailable = true,
                    isCodeSent = true,
                    updateVerificationCodeInfo = {

                    },
                    verifyCode = {

                    },
                    updatePhoneNumber = {

                    },
                    sendVerificationCode = {

                    },
                )
                Spacer(modifier = Modifier.weight(1f))
                ActiveStateButton(
                    modifier = Modifier.height(48.dp),
                    textRes = R.string.mypage_myinfo_edit_complete,
                    // TODO 개발 중 네비게이션을 위한 버튼 활성화, 추후 제거
                    // enabled = isFormValid,
                    enabled = true,
                    onClick = {

                    },
                    textStyle = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
@Preview
private fun PreviewEditMyInfoScreen() {
    EditMyInfoScreen(
        onCloseClick = {},
        onSaveClick = {},
    )
}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .height(76.dp)
            .width(76.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pictogram_yoga), // Replace with your image resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
        )
        IconButton(
            onClick = { /* TODO: Handle click */ },
            modifier = Modifier
                .offset(x = 7.dp, y = (-7).dp)
                .size(24.dp)
                .background(HobbyLoopColor.Gray40, CircleShape)
                .border(width = 2.dp, color = Color.White, CircleShape)
                .padding(2.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = HobbyLoopColor.Gray100
            )
        }
    }
}

@Composable
@Preview
fun PreviewProfileImage() {
    ProfileImage()
}