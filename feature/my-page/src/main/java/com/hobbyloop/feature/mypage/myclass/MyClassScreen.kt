package com.hobbyloop.feature.mypage.myclass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hobbyloop.ui.R
import theme.HobbyLoopTypo

@Composable
fun MyClassScreen(
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    onMyClassDetail: () -> Unit,
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    "수업 내역",
                    style = HobbyLoopTypo.head16,
                    modifier = Modifier.align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onCloseClick()
                        }
                        .align(Alignment.CenterStart)
                )
            }
        }
    ) { padding ->
        Column(
            modifier =
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(modifier = Modifier.clickable {
                onMyClassDetail()
            }, text = "수업아이템클릭")
        }
    }
}