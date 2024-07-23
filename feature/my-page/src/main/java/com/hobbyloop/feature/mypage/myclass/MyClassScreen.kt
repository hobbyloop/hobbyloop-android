package com.hobbyloop.feature.mypage.myclass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
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
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    "2024년 5월",
                    style = HobbyLoopTypo.head16
                )
                Spacer(modifier = Modifier.width(14.dp))
                Image(painter = painterResource(id = R.drawable.ic_right), contentDescription = null)
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
            ) {
                Text(text = "2023년 5월 12일", style = HobbyLoopTypo.head18)
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMyClassDetail()
                    }
                    .background(
                        color = HobbyLoopColor.Gray20, shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 8.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 8.dp
                        )
                    )) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "6:1 체형교정 필라테스", style = HobbyLoopTypo.head18)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                modifier = Modifier
                                    .background(color = HobbyLoopColor.Gray40, shape = RoundedCornerShape(size = 999.dp))
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                text = "취소가능", style = HobbyLoopTypo.head14
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "필라피티 스튜디오", style = HobbyLoopTypo.body14)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "이민주 강사님", style = HobbyLoopTypo.body14)
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(thickness = 1.dp, color = HobbyLoopColor.Gray40)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(id = R.drawable.ic_time_color), contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "2023.05.12 금", style = HobbyLoopTypo.body14)
                            ItemDivider()
                            Text(text = "20:00 - 20:50", style = HobbyLoopTypo.body14)
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(color = HobbyLoopColor.Gray20)
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text(text = "2023년 5월 12일", style = HobbyLoopTypo.head18)
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMyClassDetail()
                    }
                    .background(
                        color = HobbyLoopColor.Gray20, shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 8.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 8.dp
                        )
                    )) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "6:1 체형교정 필라테스", style = HobbyLoopTypo.head18)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                modifier = Modifier
                                    .background(color = HobbyLoopColor.Gray40, shape = RoundedCornerShape(size = 999.dp))
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                text = "취소가능", style = HobbyLoopTypo.head14
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "필라피티 스튜디오", style = HobbyLoopTypo.body14)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "이민주 강사님", style = HobbyLoopTypo.body14)
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(thickness = 1.dp, color = HobbyLoopColor.Gray40)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(id = R.drawable.ic_time_color), contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "2023.05.12 금", style = HobbyLoopTypo.body14)
                            ItemDivider()
                            Text(text = "20:00 - 20:50", style = HobbyLoopTypo.body14)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMyClassDetail()
                    }
                    .background(
                        color = HobbyLoopColor.Gray20, shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 8.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 8.dp
                        )
                    )) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "6:1 체형교정 필라테스", style = HobbyLoopTypo.head18)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                modifier = Modifier
                                    .background(color = HobbyLoopColor.Gray40, shape = RoundedCornerShape(size = 999.dp))
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                text = "취소가능", style = HobbyLoopTypo.head14
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "필라피티 스튜디오", style = HobbyLoopTypo.body14)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "이민주 강사님", style = HobbyLoopTypo.body14)
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(thickness = 1.dp, color = HobbyLoopColor.Gray40)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(id = R.drawable.ic_time_color), contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "2023.05.12 금", style = HobbyLoopTypo.body14)
                            ItemDivider()
                            Text(text = "20:00 - 20:50", style = HobbyLoopTypo.body14)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMyClassScreen() {
    MyClassScreen(
        onCloseClick = {},
        onSaveClick = {},
        onMyClassDetail = {}
    )
}

@Composable
private fun ItemDivider() {
    Spacer(modifier = Modifier.width(8.dp))
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(10.dp)
            .background(color = HobbyLoopColor.Gray100)
    )
    Spacer(modifier = Modifier.width(8.dp))
}