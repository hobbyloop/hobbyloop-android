package com.hobbyloop.core.ui.componenet.inputField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.ActiveStateButton
import com.hobbyloop.core.ui.componenet.inputField.EnhancedInputField
import com.hobbyloop.core.ui.componenet.inputField.InfoHeadTitle
import com.hobbyloop.core.ui.util.DevicePreviews
import com.hobbyloop.ui.R

@Composable
fun PhoneNumberVerificationForm(
    phoneNumber: String,
    isPhoneNumberValid: Boolean,
    updatePhoneNumber: (String) -> Unit,
    sendVerificationCode: () -> Unit,
    code: String,
    isVerificationCodeValid: Boolean,
    showProgress: Boolean,
    updateVerificationCodeInfo: (String) -> Unit,
    verifyCode: () -> Unit,
    isResendAvailable: Boolean,
    isCodeSent: Boolean
) {

    Column {
        InfoHeadTitle(text = stringResource(R.string.signup_phoneNumber))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            EnhancedInputField(
                modifier = Modifier.weight(0.65f),
                isValid = isPhoneNumberValid,
                hintText = stringResource(R.string.signup_phoneNumber_hint),
                errorText = stringResource(R.string.signup_phoneNumber_error),
                value = phoneNumber,
                valueChange = updatePhoneNumber,
                textStyle = MaterialTheme.typography.labelLarge,
                isPhoneNumber = true
            )
            ActiveStateButton(
                modifier = Modifier
                    .weight(0.35f)
                    .height(48.dp)
                    .padding(start = 8.dp),
                textRes = if (isResendAvailable) R.string.signup_reCodeSent else R.string.signup_codeSent,
                onClick = sendVerificationCode,
                enabled = isPhoneNumberValid,
                textStyle = MaterialTheme.typography.labelLarge
            )
        }

        if (isCodeSent) {
            Spacer(modifier = Modifier.height(10.dp))
            VerificationCodeInput(
                code = code,
                isVerificationCodeValid = isVerificationCodeValid,
                showProgress = showProgress,
                updateVerificationCodeInfo = updateVerificationCodeInfo,
                verifyCode = verifyCode
            )
        }
    }
}


@Composable
fun VerificationCodeInput(
    code: String,
    isVerificationCodeValid: Boolean,
    showProgress: Boolean,
    updateVerificationCodeInfo: (String) -> Unit,
    verifyCode: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.Top
    ) {

        EnhancedInputField(
            modifier = Modifier.weight(0.65f),
            value = code,
            hintText = stringResource(R.string.signup_code_hint),
            errorText = stringResource(R.string.signup_code_error),
            isValid = true,
            valueChange = updateVerificationCodeInfo,
            keyboardController = keyboardController,
            textStyle = MaterialTheme.typography.labelLarge
        )

        ActiveStateButton(
            modifier = Modifier
                .height(48.dp)
                .weight(0.35f)
                .padding(start = 8.dp),
            textRes = if (isVerificationCodeValid) R.string.signup_codeVerified else R.string.signup_checkCode,
            onClick = {
                verifyCode()
                keyboardController?.hide()
            },
            enabled = !isVerificationCodeValid,
            textStyle = MaterialTheme.typography.labelLarge,
            showProgress = showProgress
        )
    }
}
