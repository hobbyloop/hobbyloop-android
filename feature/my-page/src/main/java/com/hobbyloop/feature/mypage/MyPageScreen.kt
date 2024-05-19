package com.hobbyloop.feature.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun MyPageScreen() {
    Scaffold { padding ->
        Column(
            modifier =
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(
                text = "마이페이지",
            )
        }
    }
}
