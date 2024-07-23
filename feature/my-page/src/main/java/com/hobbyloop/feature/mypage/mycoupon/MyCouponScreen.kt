package com.hobbyloop.feature.mypage.mycoupon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.core.ui.componenet.ActiveStateButton
import com.hobbyloop.core.ui.componenet.divider.VerticalDashedDivider
import com.hobbyloop.feature.signup.componenet.EnhancedInputField
import com.hobbyloop.ui.R
import kotlinx.coroutines.launch
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCouponScreen(
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: MyCouponViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is MyCouponSideEffect.ShowError -> {
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
            TopBar(onCloseClick = onCloseClick)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CouponRegisterSection(state = state)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(color = HobbyLoopColor.Gray20)
            )
            CouponListSection(state = state)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(color = HobbyLoopColor.Gray20)
            )
            CouponUsageNotes()
        }
    }
}

@Composable
private fun TopBar(onCloseClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            "쿠폰",
            style = HobbyLoopTypo.head16,
            modifier = Modifier.align(Alignment.Center)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .clickable { onCloseClick() }
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
private fun CouponRegisterSection(state: MyCouponUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_coupon), contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "쿠폰 등록")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            EnhancedInputField(
                modifier = Modifier.weight(0.65f),
                isValid = state.error == null,
                hintText = stringResource(R.string.mypage_coupon_register_hint),
                errorText = state.error ?: "",
                value = "couponNumber",
                valueChange = { },
                textStyle = MaterialTheme.typography.labelLarge,
                isPhoneNumber = true
            )
            ActiveStateButton(
                modifier = Modifier
                    .weight(0.35f)
                    .height(48.dp)
                    .padding(start = 8.dp),
                textRes = R.string.mypage_coupon_register,
                onClick = { },
                enabled = true,
                textStyle = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
private fun CouponListSection(state: MyCouponUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_coupon), contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "보유 쿠폰")
        }
        Spacer(modifier = Modifier.height(16.dp))
        state.coupons.forEach { coupon ->
            MyCouponItem(coupon)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CouponUsageNotes() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "쿠폰 사용 시 유의사항", style = HobbyLoopTypo.head14)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Kid, Adult, Senior 연령에 따라, 면밀한 움직임 분석을 통한 체계적인 레슨 및 지속적인 컨디션 캐치를 통한 운동 능력 맞춤 향상, 외부 환경으로 인한 불균형 움직임을 고려한 문적인 Pilates & Weight Program을 제공하고 있습니다.   필라테스 강사와 웨이트 트레이너가 함께, 회원님들의 몸을 더 건강하고 빛나는 라인으로 만들어 드리겠습니다.",
            style = HobbyLoopTypo.text12.copy(color = HobbyLoopColor.Gray60)
        )
        Spacer(modifier = Modifier.height(65.dp))
    }
}

@Composable
private fun MyCouponItem(coupon: CouponUiModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(178.dp)
            .border(
                width = 1.dp, color = HobbyLoopColor.Gray40, shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row {
            Column(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 16.dp)
            ) {
                Text("${coupon.discountPercentage}%", style = HobbyLoopTypo.head22.copy(color = HobbyLoopColor.Primary))
                Spacer(modifier = Modifier.height(6.dp))
                Text("[하비루프] ${coupon.description}", style = HobbyLoopTypo.body14)
                Spacer(modifier = Modifier.height(12.dp))
                Text("[일부 센터 상품 제외]", style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60))
                Spacer(modifier = Modifier.height(4.dp))
                Text("${coupon.minimumPurchaseAmount}원 구매시 사용 가능", style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60))
                Spacer(modifier = Modifier.height(4.dp))
                Text("최대 ${coupon.maximumDiscountAmount}원 할인", style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60))
                Spacer(modifier = Modifier.height(4.dp))
                Text("사용 기간 : ${coupon.expirationDateTime}", style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60))
            }
            VerticalDashedDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.ic_check), contentDescription = null)
                Text("발급완료", style = HobbyLoopTypo.caption10.copy(color = HobbyLoopColor.Gray40))
            }
        }
    }
}

@Composable
@Preview
fun PreviewMyCouponScreen() {
    MyCouponScreen(
        onCloseClick = {},
        onSaveClick = {},
    )
}
