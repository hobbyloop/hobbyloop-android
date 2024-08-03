package com.hobbyloop.feature.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hobbyloop.core.ui.componenet.*
import com.hobbyloop.core.ui.componenet.inputField.PhoneNumberVerificationForm
import com.hobbyloop.core.ui.icons.HblIcons
import com.hobbyloop.domain.entity.login.UserLoginResult
import com.hobbyloop.feature.signup.componenet.EnhancedInputField
import com.hobbyloop.feature.signup.componenet.EnhancedLeadingIconInputField
import com.hobbyloop.feature.signup.componenet.InfoHeadTitle
import com.hobbyloop.feature.signup.state.CodeInfo
import com.hobbyloop.feature.signup.state.UserInfo
import com.hobbyloop.feature.signup.state.ValidationState
import com.kimdowoo.datepicker.componenet.SpinnerDatePicker

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    userLoginResult: UserLoginResult,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val userInfo by viewModel.userInfo.collectAsStateWithLifecycle()
    val validState by viewModel.validationState.collectAsStateWithLifecycle()
    val isFormValid by viewModel.isFormValid.collectAsStateWithLifecycle()
    val codeInfo by viewModel.codeInfo.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current

    fun clearFocusAnd(action: () -> Unit) {
        focusManager.clearFocus()
        action()
    }

    ModalBottomSheet(modifier = Modifier, sheetContent = {
        SpinnerDatePicker(modifier = Modifier, onDateChanged = { year, month, day ->
            viewModel.updateField(SignUpField.BIRTHDAY, "${year}년 ${month}월 ${day}일")
        })
    }, content = { showSheet ->
        SignUpLayout(
            isLoading = isLoading,
            onBackClick = { clearFocusAnd(onBackClick) },
            userLoginResult = userLoginResult,
            showSheet = { clearFocusAnd(showSheet) },
            userInfo = userInfo,
            validState = validState,
            isFormValid = isFormValid,
            codeInfo = codeInfo,
            onUpdateField = viewModel::updateField,
            onSelectGender = { gender -> clearFocusAnd { viewModel.selectGender(gender) } },
            onUpdateVerificationCode = viewModel::updateVerificationCode,
            onVerifyCode = { clearFocusAnd(viewModel::verifyCode) },
            onSendVerificationCode = { clearFocusAnd(viewModel::sendVerificationCode) },
            onUpdateConsents = { term, consent -> clearFocusAnd { viewModel.updateConsents(term, consent) } },
            onSignUp = { clearFocusAnd { viewModel.signUp(userLoginResult) } }
        )
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpLayout(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    showSheet: () -> Unit,
    userLoginResult: UserLoginResult,
    isLoading: Boolean,
    userInfo: UserInfo,
    validState: ValidationState,
    isFormValid: Boolean,
    codeInfo: CodeInfo,
    onUpdateField: (SignUpField, String) -> Unit,
    onSelectGender: (Gender) -> Unit,
    onUpdateVerificationCode: (String) -> Unit,
    onVerifyCode: () -> Unit,
    onSendVerificationCode: () -> Unit,
    onUpdateConsents: (Terms, Boolean) -> Unit,
    onSignUp: (UserLoginResult) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {
                TopBar(
                    modifier = Modifier
                        .height(66.dp),
                    onNavigationClick = onBackClick,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
                SignUpForm(
                    Modifier.padding(16.dp),
                    showSheet = showSheet,
                    userLoginResult = userLoginResult,
                    userInfo = userInfo,
                    validState = validState,
                    isFormValid = isFormValid,
                    codeInfo = codeInfo,
                    onUpdateField = onUpdateField,
                    onSelectGender = onSelectGender,
                    onUpdateVerificationCode = onUpdateVerificationCode,
                    onVerifyCode = onVerifyCode,
                    onSendVerificationCode = onSendVerificationCode,
                    onUpdateConsents = onUpdateConsents,
                    onSignUp = onSignUp
                )
            }
        }
    }
}

@Composable
fun SignUpForm(
    modifier: Modifier,
    showSheet: () -> Unit,
    userLoginResult: UserLoginResult,
    userInfo: UserInfo,
    validState: ValidationState,
    isFormValid: Boolean,
    codeInfo: CodeInfo,
    onUpdateField: (SignUpField, String) -> Unit,
    onSelectGender: (Gender) -> Unit,
    onUpdateVerificationCode: (String) -> Unit,
    onVerifyCode: () -> Unit,
    onSendVerificationCode: () -> Unit,
    onUpdateConsents: (Terms, Boolean) -> Unit,
    onSignUp: (UserLoginResult) -> Unit
) {

    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SignUpMainTitle()

        InputInfoContent(
            value = userInfo.name,
            headerText = stringResource(id = R.string.signup_name),
            hintText = stringResource(id = R.string.signup_name_hint),
            errorText = stringResource(R.string.signup_name_error),
            isValid = validState.isNameValid,
            onValueChange = { onUpdateField(SignUpField.NAME, it) }
        )

        InputInfoContent(
            value = userInfo.nickname,
            headerText = stringResource(R.string.signup_nickname),
            hintText = stringResource(R.string.signup_nickname_hint),
            errorText = stringResource(R.string.signup_nickname_error),
            isValid = validState.isNicknameValid,
            onValueChange = { onUpdateField(SignUpField.NICKNAME, it) }
        )

        GenderSelection(
            selectedGender = userInfo.gender,
            selectGender = onSelectGender
        )

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
            updateVerificationCodeInfo = onUpdateVerificationCode,
            verifyCode = onVerifyCode,
            updatePhoneNumber = { onUpdateField(SignUpField.PHONE_NUMBER, it) },
            sendVerificationCode = onSendVerificationCode
        )

        TermsCheck(
            dataCollectionConsent = userInfo.dataCollectionConsent,
            marketingConsent = userInfo.marketingConsent,
            updateConsents = onUpdateConsents
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
                onClick = { onSignUp(userLoginResult) },
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
            onCheckedChange = { updateConsents(Terms.All, !allChecked) }
        )
        HorizontalLine(modifier = Modifier.padding(vertical = 8.dp))
        TermsCheckItem(
            label = Terms.MarketingConsent.label,
            checked = marketingConsent,
            onCheckedChange = { updateConsents(Terms.MarketingConsent, it) },
            isEssential = false
        )
        TermsCheckItem(
            label = Terms.DataCollectionConsent.label,
            checked = dataCollectionConsent,
            onCheckedChange = { updateConsents(Terms.DataCollectionConsent, it) },
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
            UnderLineClickableText(stringResource(R.string.signup_detail))
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
    val genders = Gender.entries.toTypedArray()
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
    onValueChange: (String) -> Unit
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
            valueChange = onValueChange,
        )
    }
}

@Composable
fun TermsCheckItem(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isEssential: Boolean = true
) {
    CheckboxWithLabel(
        checked = checked,
        onCheckedChange = onCheckedChange,
        label = label,
        moreInfo = true,
        isEssential = isEssential
    )
}