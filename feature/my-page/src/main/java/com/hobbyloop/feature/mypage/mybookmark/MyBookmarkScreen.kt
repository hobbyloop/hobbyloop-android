package com.hobbyloop.feature.mypage.mybookmark

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun MyBookmarkScreen(
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    "보관함",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = HobbyLoopColor.Gray40,
                            shape = RoundedCornerShape(size = 999.dp),
                        )
                        .padding(horizontal = 14.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_updown), contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "추천순", style = HobbyLoopTypo.body14.copy(color = HobbyLoopColor.Gray100)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.ic_align_grid_16), contentDescription = null)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(color = HobbyLoopColor.Gray20)
            )
            val hotTicketItems: List<HotTicketUiModel> = listOf(
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
                HotTicketUiModel(
                    isRefundable = true, isBookMarked = true,
                    imageUrl = "",
                    location = "서울 강남구",
                    centerName = "필라피티 스튜디오",
                    price = "350,000원 ~",
                    rating = "4.8",
                    countOfReview = "(12)",
                ),
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(hotTicketItems) { hotTicket ->
                    Ticket(hotTicket) {
                        Log.d("HotTicketSection", "onBookmarked")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMyBookmarkScreen() {
    MyBookmarkScreen(
        onCloseClick = {},
        onSaveClick = {},
    )
}

data class HotTicketUiModel(
    val isRefundable: Boolean,
    val isBookMarked: Boolean,
    val imageUrl: String,
    val location: String,
    val centerName: String,
    val price: String,
    val rating: String,
    val countOfReview: String,
)

@Composable
fun Ticket(
    item: HotTicketUiModel,
    onBookmarked: () -> Unit,
) {
    Column {
        Box(
            modifier = Modifier
                .size(173.dp)
                .border(
                    width = 1.dp, Color.Red, shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color.Blue, shape = RoundedCornerShape(8.dp)),
        ) {
            if (item.isRefundable) {
                Text(
                    "환불가능",
                    modifier = Modifier
                        .background(
                            color = Color(0x80000000), shape = RoundedCornerShape(
                                topStart = 8.dp,
                                topEnd = 0.dp,
                                bottomEnd = 8.dp,
                                bottomStart = 0.dp
                            )
                        )
                        .padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)
                        .align(Alignment.TopStart),
                    style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.White),
                )
            }
            Image(
                modifier = Modifier
                    .clickable {
                        onBookmarked()
                    }
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 10.dp),
                painter = painterResource(id = if (item.isBookMarked) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark_outline), contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.padding(start = 4.dp)
        ) {
            Text(
                item.location,
                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                item.centerName,
                style = HobbyLoopTypo.body14
            )
            Text(
                item.price,
                style = HobbyLoopTypo.head18.copy(color = HobbyLoopColor.Primary)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = com.hobbyloop.ui.R.drawable.ic_star_12_color), contentDescription = null)
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    item.rating,
                    style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60),
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text("(${item.countOfReview})", style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray40))
            }

        }
    }
}