package com.hobbyloop.core.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

object TextFieldUtil {

    fun isValidKoreanName(name: String): Boolean {
        val regex = Regex("^[가-힣]{2,5}$")
        return regex.matches(name)
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val validPrefixes = listOf("010", "011", "012", "013", "014", "015", "016", "017", "018", "019")
        val seoulPrefix = "02"

        if (!phoneNumber.all { it.isDigit() }) return false

        if (phoneNumber.startsWith(seoulPrefix)) {
            return phoneNumber.length == 10
        }

        return phoneNumber.length == 11 && validPrefixes.any { phoneNumber.startsWith(it) }
    }

    class PhoneNumberVisualTransformation : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val digits = text.text.filter { it.isDigit() }
            val formatted = when {
                digits.startsWith("02") && digits.length >= 9 -> "${digits.substring(0, 2)}-${digits.substring(2, 6)}-${digits.substring(6)}"
                digits.startsWith("02") && digits.length >= 6 -> "${digits.substring(0, 2)}-${digits.substring(2)}"
                digits.length >= 12 -> "${digits.substring(0, 3)}-${digits.substring(3, 7)}-${digits.substring(7, 11)}"
                digits.length >= 8 -> "${digits.substring(0, 3)}-${digits.substring(3, 7)}-${digits.substring(7)}"
                digits.length >= 4 -> "${digits.substring(0, 3)}-${digits.substring(3)}"
                else -> digits
            }
            return TransformedText(
                AnnotatedString(formatted),
                object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        val transformedOffset = when {
                            digits.startsWith("02") && offset <= 2 -> offset
                            digits.startsWith("02") && offset <= 6 -> offset + 1
                            digits.startsWith("02") && offset > 6 -> offset + 2
                            offset <= 3 -> offset
                            offset <= 7 -> offset + 1
                            offset <= 11 -> offset + 2
                            else -> offset + 3
                        }
                        return min(transformedOffset, formatted.length)
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        val originalOffset = when {
                            digits.startsWith("02") && offset <= 3 -> offset
                            digits.startsWith("02") && offset <= 8 -> offset - 1
                            digits.startsWith("02") && offset > 8 -> offset - 2
                            offset <= 4 -> offset
                            offset <= 9 -> offset - 1
                            offset <= 14 -> offset - 2
                            else -> offset - 3
                        }
                        return min(originalOffset, digits.length)
                    }
                }
            )
        }
    }

    class KoreanNameVisualTransformation : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val trimmed = if (text.text.length > 5) text.text.substring(0, 5) else text.text

            return TransformedText(
                AnnotatedString(trimmed),
                OffsetMapping.Identity
            )
        }
    }
}
