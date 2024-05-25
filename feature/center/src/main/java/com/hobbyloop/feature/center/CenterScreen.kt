package com.hobbyloop.feature.center

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hobbyloop.core.ui.componenet.AsyncImage
import com.hobbyloop.core.ui.componenet.TopBar
import com.hobbyloop.domain.model.Center
import com.hobbyloop.feature.center.filter.bottomsheet.BottomSheetContent
import com.hobbyloop.member.ui.theme.HobbyLoopTypo
import com.hobbyloop.ui.R
import kotlinx.coroutines.launch
import theme.HobbyLoopColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterScreen(
    modifier: Modifier = Modifier,
    viewModel: CenterViewModel = hiltViewModel()
) {
    val pagingCenters = viewModel.centersFlow.collectAsLazyPagingItems()
    val filterState by viewModel.filterState.collectAsState()

    // bottom sheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf<FilterType?>(null) }

    if (showBottomSheet) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            // SheetContent
            BottomSheetContent(
                selectedFilter = selectedFilter,
                filterStateUpdate = viewModel::updateFilterState,
                filterState = filterState,
                closeBottomSheet = { showBottomSheet = false }
            )
        }
    }

    CenterLayout(
        modifier = modifier,
        centers = pagingCenters,
        filterState = filterState,
        onFilterButtonClick = { filterType ->
            if (filterType == FilterType.REFUNDABLE) {
                viewModel.updateFilterState(filterState.copy(refundable = !filterState.refundable))
            } else {
                scope.launch {
                    selectedFilter = filterType
                    showBottomSheet = true
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterLayout(
    modifier: Modifier,
    centers: LazyPagingItems<Center>,
    onFilterButtonClick: (FilterType) -> Unit,
    filterState: FilterState
) {
    val listState = rememberLazyGridState()
    val showTopBar by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedVisibility(visible = showTopBar) {
            Column {
                TopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .height(66.dp),
                    title = {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(com.hobbyloop.feature.center.R.string.example),
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.logo_small),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )
                    },
                    actionIcon = {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notice),
                                contentDescription = null
                            )
                        }
                    },
                )
                com.hobbyloop.feature.center.filter.FilterRow(
                    modifier = Modifier.fillMaxWidth(),
                    filterState = filterState,
                    onFilterButtonClick = onFilterButtonClick
                )
                NowLocation(modifier = Modifier.fillMaxWidth())
            }
        }

        TicketGrid(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            centers = centers,
            listState = listState
        )
    }
}

@Composable
fun NowLocation(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),

        ) {
        Row(
            Modifier
                .padding(16.dp)
                .clickable { /* 현재 위치 설정 로직 */ },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_gps),
                contentDescription = "Location Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("현재 위치로 설정", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun TicketGrid(
    modifier: Modifier = Modifier,
    centers: LazyPagingItems<Center>,
    listState: LazyGridState
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        state = listState
    ) {
        items(centers.itemCount) { index ->
            centers[index]?.let { center ->
                Ticket(
                    item = center,
                    modifier = Modifier.padding(6.dp),
                    onBookmarked = {},
                )
            }
        }
    }
}

@Composable
fun Ticket(
    item: Center,
    modifier: Modifier = Modifier,
    onBookmarked: () -> Unit,
) {
    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .height(173.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(imageUrl = item.logoImageUrl, contentDescription = null)
                if (item.isRefundable) {
                    Text(
                        "환불가능",
                        modifier = Modifier
                            .background(
                                color = Color(0x80000000),
                                shape = RoundedCornerShape(
                                    topStart = 8.dp,
                                    bottomEnd = 8.dp
                                )
                            )
                            .padding(8.dp)
                            .align(Alignment.TopStart),
                        style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.White),
                    )
                }
                Image(
                    modifier = Modifier
                        .clickable { onBookmarked() }
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    painter = painterResource(id = if (item.isBookMarked) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark_outline),
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.padding(start = 4.dp)) {
            Text(
                item.address,
                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                item.centerName,
                style = HobbyLoopTypo.body14
            )
            Text(
                "${item.price}원 ~",
                style = HobbyLoopTypo.head18.copy(color = HobbyLoopColor.Primary)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star_12_color),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    "${item.score}",
                    style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60),
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    "(${item.reviewCount})",
                    style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray40)
                )
            }
        }
    }
}
