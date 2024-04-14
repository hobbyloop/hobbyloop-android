package com.hobbyloop.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun LoginScreen(onSignUpClick: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
        ) {
            Text(
                text = "로그인 화면",
            )
            Button(onClick = onSignUpClick) {
                Text("회원 가입 화면으로 이동")
            }
        }
    }
}