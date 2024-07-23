package com.hobbyloop.feature.mypage.mypoint

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.feature.mypage.mypoint.component.PointHistoryItem
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun MyPointScreen(
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: MyPointViewModel = hiltViewModel(),
    scrollState: ScrollState = rememberScrollState()
) {
    val state = viewModel.container.stateFlow.collectAsState()
    val selectedTab = remember { mutableStateOf(PointScreenTab.USAGE) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    "포인트",
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
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedTab.value = PointScreenTab.USAGE
                            }
                            .border(
                                width = 1.dp,
                                color = if (selectedTab.value == PointScreenTab.USAGE) HobbyLoopColor.Primary else HobbyLoopColor.Gray40,
                                shape = RoundedCornerShape(size = 999.dp),
                            )
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        text = "사용", style = HobbyLoopTypo.body14.copy(color = if (selectedTab.value == PointScreenTab.USAGE) HobbyLoopColor.Primary else HobbyLoopColor.Gray100)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedTab.value = PointScreenTab.EXPIRATION
                            }
                            .border(
                                width = 1.dp,
                                color = if (selectedTab.value == PointScreenTab.EXPIRATION) HobbyLoopColor.Primary else HobbyLoopColor.Gray40,
                                shape = RoundedCornerShape(size = 999.dp),
                            )
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        text = "소멸 예정", style = HobbyLoopTypo.body14.copy(color = if (selectedTab.value == PointScreenTab.EXPIRATION) HobbyLoopColor.Primary else HobbyLoopColor.Gray100)
                    )
                }
            }

            when (selectedTab.value) {
                PointScreenTab.USAGE -> {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_point_color), contentDescription = null)
                                Spacer(Modifier.width(6.dp))
                                Text(text = "보유 포인트", style = HobbyLoopTypo.head14.copy(color = HobbyLoopColor.Gray60))
                            }
                            Spacer(Modifier.height(14.dp))
                            Text(text = "${state.value.point.totalPoints} P", style = HobbyLoopTypo.head18)
                        }
                    }

                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .background(color = HobbyLoopColor.Gray20)
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Spacer(Modifier.height(24.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_point), contentDescription = null)
                                Spacer(Modifier.width(6.dp))
                                Text(text = "포인트 사용 내역", style = HobbyLoopTypo.head18)
                            }
                            Spacer(Modifier.height(24.dp))
                        }
                    }

//                    items(state.value.point.history) { history ->
//                        PointHistoryItem(history)
//                        Spacer(Modifier.height(24.dp))
//                    }

                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .background(color = HobbyLoopColor.Gray20)
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Spacer(modifier = Modifier.height(77.dp))
                            Text(text = "포인트 사용 시 유의사항", style = HobbyLoopTypo.head14)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Kid, Adult, Senior 연령에 따라, 면밀한 움직임 분석을 통한 체계적인 레슨 및 지속적인 컨디션 캐치를 통한 운동 능력 맞춤 향상, 외부 환경으로 인한 불균형 움직임을 고려한 문적인 Pilates & Wegiht Program을 제공하고 있습니다. 필라테스 강사와 웨이트 트레이너가 함께, 회원님들의 몸을 더 건강하고 빛나는 라인으로 만들어 드리겠습니다.",
                                style = HobbyLoopTypo.text12.copy(color = HobbyLoopColor.Gray60)
                            )
                            Spacer(modifier = Modifier.height(65.dp))
                        }
                    }

                }

                PointScreenTab.EXPIRATION -> {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_point_color), contentDescription = null)
                                Spacer(Modifier.width(6.dp))
                                Text(text = "소멸 예정 포인트", style = HobbyLoopTypo.head14.copy(color = HobbyLoopColor.Gray60))
                            }
                            Spacer(Modifier.height(14.dp))
                            Text(text = "${state.value.point.extinctionPoint.point} P", style = HobbyLoopTypo.head18)
                            Spacer(Modifier.height(8.dp))
                            Text(text = state.value.point.extinctionPoint.date, style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60))
                        }
                    }

                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .background(color = HobbyLoopColor.Gray20)
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Spacer(modifier = Modifier.height(365.dp))
                            Text(text = "포인트 사용 시 유의사항", style = HobbyLoopTypo.head14)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Kid, Adult, Senior 연령에 따라, 면밀한 움직임 분석을 통한 체계적인 레슨 및 지속적인 컨디션 캐치를 통한 운동 능력 맞춤 향상, 외부 환경으로 인한 불균형 움직임을 고려한 문적인 Pilates & Wegiht Program을 제공하고 있습니다. 필라테스 강사와 웨이트 트레이너가 함께, 회원님들의 몸을 더 건강하고 빛나는 라인으로 만들어 드리겠습니다.",
                                style = HobbyLoopTypo.text12.copy(color = HobbyLoopColor.Gray60)
                            )
                            Spacer(modifier = Modifier.height(65.dp))
                        }
                    }
                }
            }


        }
    }
}

enum class PointScreenTab {
    USAGE, EXPIRATION
}


@Composable
@Preview
fun PreviewMyPointScreen() {
    MyPointScreen(
        onCloseClick = {},
        onSaveClick = {},
    )
}