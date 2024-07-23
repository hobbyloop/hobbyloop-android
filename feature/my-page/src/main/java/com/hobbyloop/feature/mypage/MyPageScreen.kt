package com.hobbyloop.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.ui.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
internal fun MyPageScreen(
    onBackClick: () -> Unit,
    onSettingClick: () -> Unit,
    onEditMyInfoClick: () -> Unit,
    onMyPointClick: () -> Unit,
    onMyClassClick: () -> Unit,
    onMyTicketClick: () -> Unit,
    onMyCouponClick: () -> Unit,
    onMyBookmarkClick: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is MyPageSideEffect.ShowError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = sideEffect.message
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    "마이페이지",
                    style = HobbyLoopTypo.head16,
                    modifier = Modifier.align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_setting),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd).clickable {
                        onSettingClick()
                    }
                )
            }
        }
    ) { padding ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            state.userInfo?.let { userInfo ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .height(85.dp)
                                .width(85.dp)
                                .background(color = HobbyLoopColor.Gray40, shape = CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_pen_16), // Replace with actual user image
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.clickable {
                                onEditMyInfoClick()
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = userInfo.name, style = HobbyLoopTypo.head18)
                            Image(
                                painter = painterResource(id = R.drawable.ic_pen_16),
                                contentDescription = null,
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = userInfo.nickname, style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray40))
                            ItemDivider()
                            Text(text = userInfo.phoneNumber, style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray40))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 28.dp, top = 16.dp, end = 28.dp, bottom = 24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    onMyPointClick()
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "포인트", style = HobbyLoopTypo.body14.copy(color = HobbyLoopColor.Gray80))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "${userInfo.points}P", style = HobbyLoopTypo.head16.copy(color = HobbyLoopColor.Gray100))
                        }
                        ItemDivider()
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    onMyTicketClick()
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "이용권", style = HobbyLoopTypo.body14.copy(color = HobbyLoopColor.Gray80))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "${userInfo.ticketCount}개", style = HobbyLoopTypo.head16.copy(color = HobbyLoopColor.Gray100))
                        }
                        ItemDivider()
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    onMyCouponClick()
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "쿠폰", style = HobbyLoopTypo.body14.copy(color = HobbyLoopColor.Gray80))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "${userInfo.couponCount}개", style = HobbyLoopTypo.head16.copy(color = HobbyLoopColor.Gray100))
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(color = HobbyLoopColor.Gray20)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        MyPageMenu(text = "보관함", iconRes = R.drawable.ic_right, onMyClassClick = onMyBookmarkClick)
                        HorizontalDivider(thickness = 1.dp, color = HobbyLoopColor.Gray40)
                        MyPageMenu(text = "리뷰", iconRes = R.drawable.ic_right, onMyClassClick = {  }, isEnabled = false)
                        HorizontalDivider(thickness = 1.dp, color = HobbyLoopColor.Gray40)
                        MyPageMenu(text = "수업내역", iconRes = R.drawable.ic_right, onMyClassClick = onMyClassClick)
                        HorizontalDivider(thickness = 1.dp, color = HobbyLoopColor.Gray40)
                    }
                }
            }
            state.error?.let { error ->
                // Show error message
                Text(text = error, color = Color.Red, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun MyPageMenu(text: String, iconRes: Int, onMyClassClick: () -> Unit, isEnabled: Boolean = true) {
    val textColor = if (isEnabled) HobbyLoopColor.Gray100 else HobbyLoopColor.Gray40

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isEnabled) {
                if (isEnabled) {
                    onMyClassClick()
                }
            }
            .padding(horizontal = 16.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text, style = HobbyLoopTypo.head16.copy(color = textColor))
        Image(painter = painterResource(id = iconRes), contentDescription = null)
    }
}

@Composable
private fun ItemDivider() {
    Spacer(modifier = Modifier.width(4.dp))
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(10.dp)
            .background(color = Color(0xFFD9D9D9))
    )
    Spacer(modifier = Modifier.width(4.dp))
}

@Composable
@Preview
private fun PreviewMyPageScreen() {
    MyPageScreen(
        onBackClick = {},
        onEditMyInfoClick = {},
        onMyClassClick = {},
        onMyTicketClick = {},
        onMyPointClick = {},
        onMyCouponClick = {},
        onMyBookmarkClick = {},
        onSettingClick = {}
    )
}
