package com.hobbyloop.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.ActiveStateButton
import com.hobbyloop.core.ui.util.DevicePreviews
import com.hobbyloop.feature.signup.componenet.EnhancedInputField
import com.hobbyloop.feature.signup.componenet.InfoHeadTitle

@Composable
fun PhoneNumberVerificationForm(viewModel: SignUpViewModel) {
    val userInfo by viewModel.userInfo.collectAsState()
    val verificationState by viewModel.validationState.collectAsState()
    val codeInfo by viewModel.codeInfo.collectAsState()

    Column {
        InfoHeadTitle(text = stringResource(R.string.signup_phoneNumber))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            EnhancedInputField(
                modifier = Modifier.weight(0.65f),
                isValid = verificationState.isPhoneNumberValid,
                hintText = stringResource(R.string.signup_phoneNumber_hint),
                errorText = stringResource(R.string.signup_phoneNumber_error),
                value = userInfo.phoneNumber,
                valueChange = viewModel::updatePhoneNumber,
                textStyle = MaterialTheme.typography.labelLarge,
                isPhoneNumber = true
            )
            ActiveStateButton(
                modifier = Modifier
                    .weight(0.35f)
                    .height(48.dp)
                    .padding(start = 8.dp),
                textRes = if (codeInfo.isResendAvailable) R.string.signup_reCodeSent else R.string.signup_codeSent,
                onClick = viewModel::sendVerificationCode,
                enabled = verificationState.isPhoneNumberValid,
                textStyle = MaterialTheme.typography.labelLarge
            )
        }

        if (codeInfo.isCodeSent) {
            Spacer(modifier = Modifier.height(10.dp))
            VerificationCodeInput(viewModel)
        }
    }
}


@Composable
fun VerificationCodeInput(viewModel: SignUpViewModel) {
    val userInfo by viewModel.userInfo.collectAsState()
    val verificationState by viewModel.validationState.collectAsState()
    val codeInfo by viewModel.codeInfo.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.Top
    ) {

        EnhancedInputField(
            modifier = Modifier.weight(0.65f),
            value = codeInfo.code,
            hintText = stringResource(R.string.signup_code_hint),
            errorText = stringResource(R.string.signup_code_error),
            isValid = true,
            valueChange = viewModel::updateVerificationCode,
            keyboardController = keyboardController,
            textStyle = MaterialTheme.typography.labelLarge
        )

        ActiveStateButton(
            modifier = Modifier
                .height(48.dp)
                .weight(0.35f)
                .padding(start = 8.dp),
            textRes = if (codeInfo.isVerificationCodeValid) R.string.signup_codeVerified else R.string.signup_checkCode,
            onClick = {
                viewModel.verifyCode()
                keyboardController?.hide()
            },
            enabled = !codeInfo.isVerificationCodeValid,
            textStyle = MaterialTheme.typography.labelLarge,
            showProgress = codeInfo.showProgress
        )
    }
}

@DevicePreviews
@Composable
fun SignUpLayoutPreview() {
    SignUpScreen()
}
