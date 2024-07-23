package com.hobbyloop.feature.mypage.setting

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun MySettingScreen(
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
) {

    var showLogoutBottomSheet by remember { mutableStateOf(false) }
    var showExitBottomSheet by remember { mutableStateOf(false) }

    if (showLogoutBottomSheet) {
        ConfirmationBottomSheet(
            title = "로그아웃 할까요?",
            text = "로그아웃 하시면 알림 서비스를 받으실 수 없어요.",
            onDismiss = { showLogoutBottomSheet = false },
            onConfirm = {
                showLogoutBottomSheet = false
                Log.d("TAG", "MySettingScreen: onLogoutClick() ")
            },
            negativeText = "닫기",
            positiveText = "로그아웃 하기"
        )
    }

    if (showExitBottomSheet) {
        ConfirmationBottomSheet(
            title = "탈퇴할까요?",
            text = "탈퇴 하시면 모든 데이터가 삭제됩니다.",
            onDismiss = { showExitBottomSheet = false },
            onConfirm = {
                showExitBottomSheet = false
                Log.d("TAG", "MySettingScreen: onExitClick() ")
            },
            negativeText = "닫기",
            positiveText = "회원탈퇴 하기"
        )
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    "설정",
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
            SettingsSection()
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
                    .background(color = HobbyLoopColor.Gray20)
            )
            SupportSection()
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
                    .background(color = HobbyLoopColor.Gray20)
            )
            LogOutSection {
                showLogoutBottomSheet = true
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
                    .background(color = HobbyLoopColor.Gray20)
            )
            SignOutSection {
                showExitBottomSheet = true
            }
        }
    }
}

@Composable
private fun SignOutSection(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "탈퇴하기", style = HobbyLoopTypo.body16)
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = null
        )
    }
}

@Composable
private fun LogOutSection(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "로그아웃", style = HobbyLoopTypo.body16)
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = null
        )
    }
}

@Composable
fun SettingsSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_setting), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "앱 설정",
                style = HobbyLoopTypo.head18,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        SettingItem(text = "앱 알림 설정")
        Spacer(modifier = Modifier.height(24.dp))
        SettingItem(text = "광고 알림 설정")
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "버전 정보", style = HobbyLoopTypo.body16)
        }
    }
}

@Composable
fun SettingItem(text: String) {
    var switchState by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = HobbyLoopTypo.body16)
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switchState,
            onCheckedChange = { switchState = it },
            modifier = Modifier.height(21.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFFFFFFF),  // White color for the thumb when checked
                uncheckedThumbColor = Color(0xFFFFFFFF),  // Light gray color for the thumb when unchecked
                checkedTrackColor = Color(0xFF6200EE),  // Custom purple color for the track when checked
                uncheckedTrackColor = Color(0xFFBDBDBD),
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent,
            )
        )
    }
}

@Composable
fun SupportSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_calls), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "고객 지원 센터",
                style = HobbyLoopTypo.head18,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        SupportItem(text = "자주 묻는 질문")
        Spacer(modifier = Modifier.height(24.dp))
        SupportItem(text = "카카오톡 1:1 문의")
        Spacer(modifier = Modifier.height(24.dp))
        SupportItem(text = "서비스 약관")
    }
}

@Composable
fun SupportItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = HobbyLoopTypo.body16)
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = null
        )
    }
}

@Composable
fun LogoutAndExitSection(onLogoutClick: () -> Unit, onExitClick: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLogoutClick() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "로그아웃", fontSize = 16.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onExitClick() }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "탈퇴하기", fontSize = 16.sp)
        }
    }
}

@Composable
fun ConfirmationDialog(
    title: String,
    text: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp) },
        text = { Text(text = text, fontSize = 14.sp) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "로그아웃 하기")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "닫기")
            }
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ConfirmationBottomSheet(
    title: String,
    text: String,
    negativeText: String,
    positiveText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = HobbyLoopColor.White,
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(10.5.dp))
                    .background(Color.Gray)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                modifier = Modifier
                    .size(32.dp), painter = painterResource(id = R.drawable.ic_info_color), contentDescription = null
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = title, style = HobbyLoopTypo.head22)
            Spacer(modifier = Modifier.height(28.dp))
            Text(text = text, style = HobbyLoopTypo.body14)
            Spacer(modifier = Modifier.height(41.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F1F1))
                ) {
                    Text(text = negativeText, style = HobbyLoopTypo.head16, color = Color.Black)
                }
                Button(
                    onClick = onConfirm,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = positiveText, style = HobbyLoopTypo.head16.copy(color = HobbyLoopColor.White))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMySettingScreen() {
    MySettingScreen(
        onCloseClick = { /* Do nothing */ },
        onSaveClick = { /* Do nothing */ }
    )
}

