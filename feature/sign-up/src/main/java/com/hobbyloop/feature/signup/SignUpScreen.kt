package com.hobbyloop.feature.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hobbyloop.core.ui.componenet.ActiveStateButton
import com.hobbyloop.core.ui.componenet.HorizontalLine
import com.hobbyloop.core.ui.componenet.ModalBottomSheet
import com.hobbyloop.core.ui.componenet.TopBar
import com.hobbyloop.core.ui.componenet.UnderLineClickableText
import com.hobbyloop.core.ui.icons.HblIcons
import com.hobbyloop.feature.signup.componenet.EnhancedInputField
import com.hobbyloop.feature.signup.componenet.EnhancedLeadingIconInputField
import com.hobbyloop.feature.signup.componenet.InfoHeadTitle
import com.kimdowoo.datepicker.componenet.SpinnerDatePicker

@Composable
fun SignUpScreen(onBackClick: () -> Unit = {}, onNavigationBarClick: () -> Unit = {}) {
    val viewModel: SignUpViewModel = viewModel()
    ModalBottomSheet(
        modifier = Modifier,
        sheetContent = {
            SpinnerDatePicker(
                modifier = Modifier,
                onDateChanged = { year, month, day ->
                    viewModel.updateBirthDay("${year}년 ${month}월 ${day}일")
                }
            )
        },
        content = { showSheet ->
            SignUpLayout(
                viewModel,
                onBackClick = onBackClick,
                onNavigationBarClick = onNavigationBarClick,
                showSheet = showSheet
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpLayout(
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigationBarClick: () -> Unit,
    showSheet: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            modifier = Modifier
                .height(66.dp)
                .align(Alignment.TopCenter),
            onNavigationClick = onBackClick,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent
            )
        )
        SignUpForm(viewModel, Modifier.padding(16.dp), onNavigationBarClick, showSheet = showSheet)
    }
}

@Composable
fun SignUpForm(
    viewModel: SignUpViewModel,
    modifier: Modifier,
    onNavigationBarClick: () -> Unit,
    showSheet: () -> Unit
) {

    val userInfo by viewModel.userInfo.collectAsStateWithLifecycle()
    val validState by viewModel.validationState.collectAsStateWithLifecycle()
    val isFormValid by viewModel.isFormValid.collectAsStateWithLifecycle()
    val codeInfo by viewModel.codeInfo.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(top = 66.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        SignUpMainTitle()

        InputInfoContent(
            value = userInfo.name,
            headerText = stringResource(id = R.string.signup_name),
            hintText = stringResource(id = R.string.signup_name_hint),
            errorText = stringResource(R.string.signup_name_error),
            isValid = validState.isNameValid,
            valueChange = viewModel::updateName
        )

        InputInfoContent(
            value = userInfo.nickname,
            headerText = stringResource(R.string.signup_nickname),
            hintText = stringResource(R.string.signup_nickname_hint),
            errorText = stringResource(R.string.signup_nickname_error),
            isValid = validState.isNicknameValid,
            valueChange = viewModel::updateNickname
        )

        GenderSelection(selectedGender = userInfo.gender, selectGender = viewModel::selectGender)

        DateSelection(
            userInfo.birthDay,
            showSheet = showSheet
        )

        PhoneNumberVerificationForm(
            phoneNumber = userInfo.phoneNumber,
            isPhoneNumberValid = validState.isPhoneNumberValid,
            code = codeInfo.code,
            isVerificationCodeValid = codeInfo.isVerificationCodeValid,
            showProgress = codeInfo.showProgress,
            isResendAvailable = codeInfo.isResendAvailable,
            isCodeSent = codeInfo.isCodeSent,
            updateVerificationCodeInfo = viewModel::updateVerificationCode,
            verifyCode = viewModel::verifyCode,
            updatePhoneNumber = viewModel::updatePhoneNumber,
            sendVerificationCode = viewModel::sendVerificationCode,
        )

        TermsCheck(
            dataCollectionConsent = userInfo.dataCollectionConsent,
            marketingConsent = userInfo.marketingConsent,
            updateConsents = viewModel::updateConsents
        )

        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.signup_retouch),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            ActiveStateButton(
                modifier = Modifier.height(48.dp),
                textRes = R.string.signup_endSignUp,
                enabled = isFormValid,
                onClick = onNavigationBarClick,
                textStyle = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun TermsCheck(
    dataCollectionConsent: Boolean,
    marketingConsent: Boolean,
    updateConsents: (Terms, Boolean) -> Unit
) {
    Column {
        InfoHeadTitle(text = stringResource(id = R.string.signup_Terms), isEssential = false)
        val allChecked = dataCollectionConsent && marketingConsent
        CheckboxWithLabel(
            label = Terms.All.label,
            checked = allChecked,
            onCheckedChange = { updateConsents(Terms.All, !allChecked) })
        HorizontalLine(modifier = Modifier.padding(vertical = 8.dp))
        CheckboxWithLabel(
            label = Terms.MarketingConsent.label,
            checked = marketingConsent,
            onCheckedChange = {
                updateConsents(
                    Terms.MarketingConsent,
                    !marketingConsent
                )
            },
            moreInfo = true,
            isEssential = false
        )
        CheckboxWithLabel(
            label = Terms.DataCollectionConsent.label,
            checked = dataCollectionConsent,
            onCheckedChange = {
                updateConsents(
                    Terms.DataCollectionConsent,
                    !dataCollectionConsent
                )
            },
            moreInfo = true,
            isEssential = false
        )
    }
}


@Composable
fun CheckboxWithLabel(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    moreInfo: Boolean = false,
    isEssential: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 4.dp),
                onClick = { onCheckedChange(!checked) },
            ) {
                Icon(
                    painter = painterResource(id = if (checked) HblIcons.check.resourceId else HblIcons.unCheck.resourceId),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Text(
                text = label + if (!isEssential) " [선택]" else "",
                style = MaterialTheme.typography.labelLarge
            )
        }
        if (moreInfo) {
            UnderLineClickableText("자세히")
        }
    }
}

@Composable
fun DateSelection(birthDay: String, showSheet: () -> Unit) {
    Column {
        InfoHeadTitle(text = stringResource(id = R.string.signup_birthday))
        EnhancedLeadingIconInputField(
            leadingIcon = HblIcons.calendar.resourceId,
            onClick = showSheet,
            value = birthDay,
            textStyle = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun SignUpMainTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(stringResource(R.string.signup_welcome), style = MaterialTheme.typography.titleLarge)
        Text(
            stringResource(R.string.signup_info),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun GenderSelection(selectedGender: Gender?, selectGender: (Gender) -> Unit) {
    val genders = Gender.entries
    val shape = RoundedCornerShape(8.dp)

    Column {
        InfoHeadTitle(text = stringResource(R.string.signup_gender))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genders.forEach { gender ->
                OutlinedButton(
                    onClick = { selectGender(gender) },
                    shape = shape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (selectedGender == gender) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                ) {
                    Text(
                        gender.label,
                        color = if (selectedGender == gender) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

@Composable
fun InputInfoContent(
    modifier: Modifier = Modifier,
    headerText: String = "",
    hintText: String = "",
    errorText: String = "",
    isEssential: Boolean = true,
    isValid: Boolean,
    value: String,
    valueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        InfoHeadTitle(text = headerText, isEssential = isEssential)
        Spacer(modifier = Modifier.height(8.dp))
        EnhancedInputField(
            modifier = Modifier.fillMaxWidth(),
            hintText = hintText,
            isValid = isValid,
            errorText = errorText,
            value = value,
            valueChange = valueChange,
        )
    }
}




