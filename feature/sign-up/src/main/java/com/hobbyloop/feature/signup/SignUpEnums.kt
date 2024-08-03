package com.hobbyloop.feature.signup

enum class SignUpField {
    NAME, NICKNAME, PHONE_NUMBER, BIRTHDAY
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
