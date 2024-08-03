package com.hobbyloop.feature.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.entity.login.UserLoginResult
import com.hobbyloop.domain.entity.signup.SignUpInfo
import com.hobbyloop.domain.usecase.signup.SignUpUseCase
import com.hobbyloop.domain.usecase.user.SetUserDataUseCase
import com.hobbyloop.feature.signup.state.CodeInfo
import com.hobbyloop.feature.signup.state.UserInfo
import com.hobbyloop.feature.signup.state.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val setUserDataUseCase: SetUserDataUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    companion object {
        const val PHONE_NUMBER_LENGTH = 11
        const val PHONE_NUMBER_PREFIX = "010"
        const val MIN_NAME_LENGTH = 2
        const val MIN_NICKNAME_LENGTH = 2
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일")
    }

    private val _userInfo = MutableStateFlow(UserInfo())
    val userInfo: StateFlow<UserInfo> = _userInfo.asStateFlow()

    private val _validationState = MutableStateFlow(ValidationState())
    val validationState: StateFlow<ValidationState> = _validationState.asStateFlow()

    private val _codeInfo = MutableStateFlow(CodeInfo())
    val codeInfo: StateFlow<CodeInfo> = _codeInfo.asStateFlow()

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun updateField(field: SignUpField, value: String) {
        val isValid = validateField(field, value)

        _userInfo.update { currentUserInfo ->
            when (field) {
                SignUpField.NAME -> currentUserInfo.copy(name = value)
                SignUpField.NICKNAME -> currentUserInfo.copy(nickname = value)
                SignUpField.PHONE_NUMBER -> {
                    resetCodeInfo()
                    currentUserInfo.copy(phoneNumber = value)
                }
                SignUpField.BIRTHDAY -> currentUserInfo.copy(birthDay = value)
            }
        }

        _validationState.update { currentState ->
            when (field) {
                SignUpField.NAME -> currentState.copy(isNameValid = isValid)
                SignUpField.NICKNAME -> currentState.copy(isNicknameValid = isValid)
                SignUpField.PHONE_NUMBER -> currentState.copy(isPhoneNumberValid = isValid)
                SignUpField.BIRTHDAY -> currentState.copy(isBirthDayValid = isValid)
            }
        }

        updateFormValidity()
    }

    fun selectGender(gender: Gender) {
        _userInfo.update { it.copy(gender = gender) }
        _validationState.update { it.copy(isGenderSelected = true) }
        updateFormValidity()
    }

    fun sendVerificationCode() {
        viewModelScope.launch {
            _codeInfo.update {
                it.copy(
                    isCodeSent = true,
                    isResendAvailable = true,
                    isVerificationCodeValid = false
                )
            }
            updateFormValidity()
        }
    }

    fun updateVerificationCode(newCode: String) {
        _codeInfo.update { it.copy(code = newCode, isVerificationCodeValid = false) }
        updateFormValidity()
    }

    fun verifyCode() {
        viewModelScope.launch {
            _codeInfo.update { it.copy(isVerificationCodeValid = false, showProgress = true) }
            delay(2000)
            _codeInfo.update { it.copy(isVerificationCodeValid = true, showProgress = false) }
            updateFormValidity()
        }
    }

    fun updateConsents(term: Terms, checked: Boolean) {
        _userInfo.update {
            when (term) {
                Terms.All -> it.copy(
                    marketingConsent = checked,
                    dataCollectionConsent = checked
                )
                Terms.MarketingConsent -> it.copy(marketingConsent = checked)
                Terms.DataCollectionConsent -> it.copy(dataCollectionConsent = checked)
            }
        }
    }

    fun signUp(userLoginResult: UserLoginResult) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val signUpInfo = SignUpInfo(
                    name = userInfo.value.name,
                    email = userLoginResult.email ?: "",
                    nickname = userInfo.value.nickname,
                    gender = userInfo.value.gender?.ordinal ?: 0,
                    birthday = LocalDate.parse(userInfo.value.birthDay, dateFormatter),
                    phoneNumber = userInfo.value.phoneNumber,
                    isOption1 = userInfo.value.marketingConsent,
                    isOption2 = userInfo.value.dataCollectionConsent,
                    provider = userLoginResult.provider ?: "",
                    subject = userLoginResult.subject ?: "",
                    oauth2AccessToken = userLoginResult.oauth2AccessToken ?: "",
                    ci = "", // Add CI value if needed
                    di = "" // Add DI value if needed
                )
                val signupResponse = signUpUseCase(signUpInfo)
                setUserDataUseCase.setJwt(signupResponse.accessToken)
            } catch (e: Exception) {
                Log.d("SignUpViewModel", "signUp: $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun updateFormValidity() {
        val state = validationState.value
        _isFormValid.value =
            state.isNameValid && state.isNicknameValid && state.isPhoneNumberValid &&
                    state.isGenderSelected && codeInfo.value.isVerificationCodeValid
    }

    private fun validateField(field: SignUpField, value: String): Boolean {
        return when (field) {
            SignUpField.NAME -> value.length >= MIN_NAME_LENGTH
            SignUpField.NICKNAME -> value.length >= MIN_NICKNAME_LENGTH
            SignUpField.PHONE_NUMBER -> value.length == PHONE_NUMBER_LENGTH && value.startsWith(PHONE_NUMBER_PREFIX)
            SignUpField.BIRTHDAY -> value.isNotBlank()
        }
    }

    private fun resetCodeInfo() {
        _codeInfo.update {
            it.copy(
                isCodeSent = false,
                isResendAvailable = false,
                isVerificationCodeValid = false
            )
        }
    }
}

