package com.hobbyloop.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.HorizontalLine
import com.hobbyloop.core.ui.icons.HblIcons

@Composable
internal fun LoginScreen(onSignUpClick: () -> Unit) {
    LoginScreens(onSignUpClick = onSignUpClick)
}

// todo loginApi 구현시 변경
@Composable
fun LoginScreens(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Icon(painter = painterResource(id = HblIcons.AppLogo.resourceId), contentDescription = null)
        Spacer(modifier = Modifier.height(181.dp))
        LoginButtons(onSignUpClick)
        Spacer(modifier = Modifier.height(83.dp))
        HorizontalLine(Modifier.padding(PaddingValues(horizontal = 20.dp)))
        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = stringResource(R.string.lost_account),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun LoginButtons(
    onSignUpClick: () -> Unit,
    kakaoLoginClick: () -> Unit = {
        // todo kakaoLoginLocic
        onSignUpClick()
    },
    naverLoginClick: () -> Unit = {
        // todo naverLoginLocic
        onSignUpClick()
    },
    googleLoginClick: () -> Unit = {
        // todo googleLoginLocic
        onSignUpClick()
    },
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LoginButton(
            buttonText = stringResource(R.string.kakao_login),
            onClick = kakaoLoginClick,
            backgroundColor = Color(0xFFFEE500),
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_android_black_24dp),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        )
        LoginButton(
            buttonText = stringResource(R.string.google_login),
            onClick = naverLoginClick,
            backgroundColor = Color.White,
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_android_black_24dp),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        )
        LoginButton(
            buttonText = stringResource(R.string.naver_login),
            onClick = googleLoginClick,
            backgroundColor = Color(0xFF03C75A),
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_android_black_24dp),
                    contentDescription = null,
                    tint = Color.White
                )
            },
            textColor = Color.White
        )
    }
}

@Composable
fun LoginButton(
    buttonText: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = Color.Black,
    leadingIcon: @Composable (() -> Unit),
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
        ,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(vertical = 12.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        leadingIcon()
        Text(
            text = buttonText,
            color = textColor,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun LoginButtonsPreview() {
    MaterialTheme {
        LoginButtons(onSignUpClick = {})
    }
}

