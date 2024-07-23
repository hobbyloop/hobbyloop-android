package com.hobbyloop.feature.mypage.myticket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.ui.R
import kotlinx.coroutines.launch
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun MyTicketScreen(
    onCloseClick: () -> Unit,
    onBuyTicketClick: () -> Unit,
    viewModel: MyTicketViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is MyTicketSideEffect.ShowError -> {
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
        topBar = { TopBar(onCloseClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TicketTabRow(state.selectedTab, viewModel::onTabSelected)

            Spacer(modifier = Modifier.height(16.dp))

            when (state.selectedTab) {
                TicketTab.USAGE -> {
                    if (state.tickets.isEmpty()) {
                        EmptyTicketView(onBuyTicketClick)
                    } else {
                        state.tickets.forEach { ticket ->
                            TicketItem(ticket)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
                TicketTab.HISTORY -> {
                    state.history.forEach { monthHistory ->
                        MonthHistoryView(monthHistory)
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(onCloseClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            "이용권",
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
fun TicketTabRow(selectedTab: TicketTab, onTabSelected: (TicketTab) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        TicketTab.values().forEach { tab ->
            Text(
                text = tab.title,
                style = HobbyLoopTypo.body14.copy(
                    color = if (selectedTab == tab) HobbyLoopColor.Primary else HobbyLoopColor.Gray100
                ),
                modifier = Modifier
                    .clickable { onTabSelected(tab) }
                    .border(
                        width = 1.dp,
                        color = if (selectedTab == tab) HobbyLoopColor.Primary else HobbyLoopColor.Gray40,
                        shape = RoundedCornerShape(999.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun TicketItem(ticket: TicketHistoryUiModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(178.dp)
            .background(color = HobbyLoopColor.Gray40, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.img_ticket_3_month),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(ticket.centerName, style = HobbyLoopTypo.body14)
                Text(ticket.ticketName, style = HobbyLoopTypo.head18)
                Text("잔여 횟수 ${ticket.remainingCount}/${ticket.totalCounting}회", style = HobbyLoopTypo.body14)
            }
        }
    }
}

@Composable
fun MonthHistoryView(monthHistory: MonthHistoryUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text("${monthHistory.yearMonth}", style = HobbyLoopTypo.head14)
        Spacer(modifier = Modifier.height(8.dp))
        monthHistory.usingHistories.forEach { history ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(history.usedAt, style = HobbyLoopTypo.body14)
                Text("${history.useCount}회 사용", style = HobbyLoopTypo.body14)
                Text("${history.remainingCount}회 남음", style = HobbyLoopTypo.body14)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun EmptyTicketView(onBuyTicketClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("등록된 이용권이 없어\n수업을 예약할 수 없어요 😢", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBuyTicketClick) {
            Text("이용권 구매하러 가기")
        }
    }
}

enum class TicketTab(val title: String) {
    USAGE("사용"),
    HISTORY("소멸")
}
