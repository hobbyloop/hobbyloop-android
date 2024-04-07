package com.hobbyloopapp.feature.signup.signupform

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
fun SignUpFormScreen(
    onBackClick: () -> Unit,
    onSignupClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "회원가입 화면 입니다.")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onBackClick,
            content = {
                Text("뒤로 가기")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onSignupClick()
            },
            content = {
                Text("회원 가입 성공 버튼")
            }
        )
    }
}
