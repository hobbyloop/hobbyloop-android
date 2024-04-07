package com.hobbyloop.feature.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SingUpScreen(
    onBackClick: () -> Unit,
    onNavigationBarClick: () -> Unit,
) {
    Scaffold { padding ->
        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
        ) {
            Text(
                text = "회원가입",
            )
            Button(onClick = onBackClick) {
                Text("로그인 화면으로 이동")
            }
            Button(onClick = onNavigationBarClick) {
                Text("가입완료 navigationBar home으로 이동")
            }
        }
    }
}