package com.hobbyloop.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.feature.signup.state.CodeInfo
import com.hobbyloop.feature.signup.state.UserInfo
import com.hobbyloop.feature.signup.state.ValidationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _userInfo = MutableStateFlow(UserInfo())
    val userInfo: StateFlow<UserInfo> = _userInfo.asStateFlow()

    private val _validationState = MutableStateFlow(ValidationState())
    val validationState: StateFlow<ValidationState> = _validationState.asStateFlow()

    private val _codeInfo = MutableStateFlow(CodeInfo())
    val codeInfo: StateFlow<CodeInfo> = _codeInfo.asStateFlow()

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid.asStateFlow()

    fun updateName(newName: String) {
        val isValid = validateName(newName)
        _userInfo.value = _userInfo.value.copy(name = newName)
        _validationState.value = _validationState.value.copy(isNameValid = isValid)
        updateFormValidity()
    }

    fun updateNickname(newNickname: String) {
        val isValid = validateNickname(newNickname)
        _userInfo.value = _userInfo.value.copy(nickname = newNickname)
        _validationState.value = _validationState.value.copy(isNicknameValid = isValid)
        updateFormValidity()
    }

    fun updatePhoneNumber(newNumber: String) {
        val isValid = validatePhoneNumber(newNumber)
        _userInfo.value = _userInfo.value.copy(phoneNumber = newNumber)
        _validationState.value = _validationState.value.copy(isPhoneNumberValid = isValid)
        _codeInfo.value = _codeInfo.value.copy(
            isCodeSent = false,
            isResendAvailable = false,
            isVerificationCodeValid = false
        )
    }


    fun updateBirthDay(newBirthDay: String) {
        _userInfo.value = _userInfo.value.copy(birthDay = newBirthDay)
        _validationState.value = _validationState.value.copy(isNameValid = newBirthDay.isNotBlank())
        updateFormValidity()
    }

    fun selectGender(gender: Gender) {
        _userInfo.value = _userInfo.value.copy(gender = gender)
        _validationState.value = _validationState.value.copy(isGenderSelected = true)
        updateFormValidity()
    }

    fun sendVerificationCode() {
        viewModelScope.launch {
            _codeInfo.value = _codeInfo.value.copy(
                isCodeSent = true,
                isResendAvailable = true,
                isVerificationCodeValid = false
            )
        }
        updateFormValidity()
    }

    fun updateVerificationCode(newCode: String) {
        _codeInfo.value = _codeInfo.value.copy(code = newCode, isVerificationCodeValid = false)
        updateFormValidity()
    }

    fun verifyCode() {
        viewModelScope.launch {
            _codeInfo.value =
                _codeInfo.value.copy(isVerificationCodeValid = false, showProgress = true)
            delay(2000)
            _codeInfo.value =
                _codeInfo.value.copy(isVerificationCodeValid = true, showProgress = false)
            updateFormValidity()
        }
    }

    fun updateConsents(term: Terms, checked: Boolean) {
        when (term) {
            Terms.All -> _userInfo.value =
                _userInfo.value.copy(marketingConsent = checked, dataCollectionConsent = checked)

            Terms.MarketingConsent -> _userInfo.value =
                _userInfo.value.copy(marketingConsent = checked)

            Terms.DataCollectionConsent -> _userInfo.value =
                _userInfo.value.copy(dataCollectionConsent = checked)
        }
    }


    private fun updateFormValidity() {
        val state = validationState.value
        _isFormValid.value = state.isNameValid && state.isNicknameValid && state.isPhoneNumberValid && state.isGenderSelected && codeInfo.value.isVerificationCodeValid
    }

    private fun validateName(name: String): Boolean = name.length >= 2
    private fun validateNickname(nickname: String): Boolean = nickname.length >= 2
    private fun validatePhoneNumber(phone: String): Boolean =
        phone.length == 11 && phone.startsWith("010")
}


enum class Gender(val label: String) {
    Male("남성"),
    Female("여성")
}


enum class Terms(val label: String) {
    All("전체 동의"),
    MarketingConsent("마케팅 수신 정보 동의"),
    DataCollectionConsent("마케팅 정보 수집 동의")
}