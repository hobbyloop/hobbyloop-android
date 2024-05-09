package com.hobbyloop.feature.home.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.home.model.HotTicketUiModel
import com.hobbyloop.member.ui.theme.HobbyLoopTypo
import com.hobbyloop.ui.R
import theme.HobbyLoopColor

@Composable
fun HotTicketSection(
    modifier: Modifier = Modifier,
    firstText: String,
    highlightText: String,
    lastText: String,
    items: List<HotTicketUiModel> = listOf(),
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_ticket), contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                firstText,
                style = HobbyLoopTypo.head18
            )
            Text(
                highlightText,
                style = HobbyLoopTypo.head18.copy(color = HobbyLoopColor.Primary)
            )
            Text(
                lastText,
                style = HobbyLoopTypo.head18
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(R.drawable.ic_right), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(items) { hotTicket ->
                Ticket(hotTicket) {
                    Log.d("HotTicketSection", "onBookmarked")
                }
            }
        }
    }
}

@Preview(name = "HotTicketSection")
@Composable
private fun PreviewHotTicketSection() {
    HotTicketSection(
        firstText = "이번주",
        highlightText = " HOT ",
        lastText = "이용권"
    )
}

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
                Image(painter = painterResource(id = com.hobbyloop.ui.R.drawable.ic_star), contentDescription = null)
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

@Preview(showBackground = true)
@Composable
fun PreviewTicket() {
    Ticket(
        item = HotTicketUiModel(
            isRefundable = true, isBookMarked = true,
            imageUrl = "",
            location = "서울 강남구",
            centerName = "필라피티 스튜디오",
            price = "350,000원 ~",
            rating = "4.8",
            countOfReview = "(12)",
        ),
        onBookmarked = {

        }
    )
}
