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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.ActiveStateButton
import com.hobbyloop.core.ui.componenet.TopBar
import com.hobbyloop.feature.signup.componenet.EnhancedInputField
import com.hobbyloop.feature.signup.componenet.InfoHeadTitle

@Composable
fun SignUpScreen(onBackClick: () -> Unit = {}, onNavigationBarClick: () -> Unit = {}) {
    val viewModel = SignUpViewModel()
    SignUpLayout(viewModel, onBackClick = onBackClick, onNavigationBarClick = onNavigationBarClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpLayout(
    viewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigationBarClick: () -> Unit
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
        SignUpForm(viewModel, Modifier.padding(16.dp), onNavigationBarClick)
    }
}

@Composable
fun SignUpForm(viewModel: SignUpViewModel, modifier: Modifier, onNavigationBarClick: () -> Unit) {

    val userInfo by viewModel.userInfo.collectAsState()
    val validState by viewModel.validationState.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()

    Column(
        modifier = modifier
            .padding(top = 66.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // 메인 타이틀
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
            errorText =  stringResource(R.string.signup_nickname_error),
            isValid = validState.isNicknameValid,
            valueChange = viewModel::updateNickname
        )

        // 성별
        GenderSelection(selectedGender = userInfo.gender, selectGender = viewModel::selectGender)

        PhoneNumberVerificationForm(viewModel = viewModel)

        Spacer(Modifier.weight(1f))

        // 회원가입 완료 버튼
        ActiveStateButton(
            textRes = R.string.signup_endSignUp,
            enabled = isFormValid,
            onClick = onNavigationBarClick,
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
            color = Color(0xFFA0A0A0)
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
@OptIn(ExperimentalComposeUiApi::class)
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




