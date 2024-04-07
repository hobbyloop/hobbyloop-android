package com.hobbyloopapp.feature.login.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onNavigateToSignupScreen: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onLoginClick,
            content = {
                Text("로그인")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onForgotPasswordClick,
            content = {
                Text("계정찾기 화면")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onNavigateToSignupScreen,
            content = {
                Text("회원가입 화면")
            }
        )
    }
}
