package com.hobbyloop.core.ui.util

import java.text.SimpleDateFormat
import java.util.Locale

object TextUtil {

    fun String.formatAsPhoneNumber(): String {
        return if (this.length == 11) {
            "${this.substring(0, 3)}-${this.substring(3, 7)}-${this.substring(7, 11)}"
        } else {
            this
        }
    }

    fun String.extractFirstName(): String {
        val commonSurnames = listOf(
            "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "오", "한", "신", "서", "권", "황", "안", "송", "류", "전",
            "홍", "고", "문", "양", "손", "배", "조", "백", "허", "남", "심", "구", "곽", "성", "차", "주", "우", "나", "하", "전",
            "민", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "변", "염", "변", "여", "제", "복", "유", "추", "은",
            "편", "용", "두", "후", "소", "선", "설", "마", "길", "명", "기", "표", "반", "라", "장", "육", "단", "부", "위", "가",
            "주", "위", "금", "노", "우", "표", "고", "봉", "봉", "화", "파", "범", "화", "후", "백", "황보", "남궁", "김", "사공", "제갈",
            "선우", "독고", "동방", "황보", "사공", "제갈", "남궁", "서문", "선우", "독고", "동방"
        )

        if (this.length == 2) {
            return this // 이름 전체가 두 글자인 경우 그대로 반환
        }
        commonSurnames.sortedByDescending { it.length }.forEach { surname ->
            if (this.startsWith(surname)) {
                return this.removePrefix(surname)
            }
        }
        return this
    }

    fun String.toTicketInfoFormattedString(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm - HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy. MM. dd EEE HH:mm - HH:mm", Locale.getDefault())

        return try {
            val datePart = this.substring(0, 16)
            val timePart = this.substring(16)

            val date = inputFormat.parse("$datePart$timePart")

            outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            this
        }
    }
}
