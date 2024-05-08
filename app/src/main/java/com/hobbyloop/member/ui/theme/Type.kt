package com.hobbyloop.member.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hobbyloop.ui.R.*

// Set of Material typography styles to start with
// todo fontFamily 적용
val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        // loginButton ...
        labelLarge =
            TextStyle(
                fontFamily = FontFamily(Font(font.pretendard)),
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
            ),
        // signupTitle ...
        titleLarge =
            TextStyle(
                fontFamily = FontFamily(Font(font.pretendard)),
                fontSize = 22.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W700,
            ),
        // signupSubTitle
        titleMedium =
            TextStyle(
                fontFamily = FontFamily(Font(font.pretendard_bold)),
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
            ),
        titleSmall =
            TextStyle(
                fontFamily = FontFamily(Font(font.pretendard_bold)),
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
            ),
                /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
                 */
    )