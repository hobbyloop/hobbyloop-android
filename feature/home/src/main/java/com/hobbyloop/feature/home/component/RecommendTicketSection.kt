package com.hobbyloop.feature.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hobbyloop.feature.home.model.RecommendTicketUiModel
import com.hobbyloop.feature.home.model.TicketCategory
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun RecommendTicketSection(
    modifier: Modifier = Modifier,
    category: TicketCategory,
    items: List<RecommendTicketUiModel> = listOf(),
    @DrawableRes categoryIconId: Int = com.hobbyloop.ui.R.drawable.ic_pt,
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
            Image(
                modifier = Modifier.size(26.dp),
                painter = painterResource(id = categoryIconId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                category.description,
                style = HobbyLoopTypo.head18.copy(color = HobbyLoopColor.Primary)
            )
            Text(
                " 는 어떠세요?",
                style = HobbyLoopTypo.head18
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(items) { recommendTicket ->
                BigTicket(item = recommendTicket) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRecommendTicketSection() {
    RecommendTicketSection(
        category = TicketCategory.PT,
        items = listOf(
            RecommendTicketUiModel(
                isBookMarked = true,
                imageUrl = "",
                location = "서울 강남구",
                category = TicketCategory.PILATES,
                centerName = "필라피티 스튜디오",
                price = "350,000원 ~",
                rating = "4.8",
                reviewCount = "(12)",
            ),
            RecommendTicketUiModel(
                isBookMarked = true,
                imageUrl = "",
                location = "서울 강남구",
                category = TicketCategory.PILATES,
                centerName = "필라피티 스튜디오",
                price = "350,000원 ~",
                rating = "4.8",
                reviewCount = "(12)",
            ),
            RecommendTicketUiModel(
                isBookMarked = true,
                imageUrl = "",
                location = "서울 강남구",
                category = TicketCategory.PILATES,
                centerName = "필라피티 스튜디오",
                price = "350,000원 ~",
                rating = "4.8",
                reviewCount = "(12)",
            )
        )
    )
}

@Composable
fun BigTicket(
    modifier: Modifier = Modifier,
    item: RecommendTicketUiModel,
    onBookmarked: () -> Unit,
) {
    val isBookmarked = remember { mutableStateOf(item.isBookMarked) }
    Column(
        modifier = modifier
            .width(280.dp)
    ) {
        Box(
            modifier = Modifier
                .height(140.dp)
                .width(280.dp)

        ) {
            AsyncImage(model = item.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        shape = RoundedCornerShape(8.dp)
                        clip = true
                    }
            )
            Image(
                modifier = Modifier
                    .clickable {
                        isBookmarked.value = !isBookmarked.value
                        onBookmarked()
                    }
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 10.dp),
                painter = painterResource(id = if (isBookmarked.value) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark_outline), contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.padding(start = 4.dp)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("${item.category.description} | ${item.location}", style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60))
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_star_12_color), contentDescription = null)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(item.rating, style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60))
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("(${item.reviewCount})", style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray40))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(item.centerName, style = HobbyLoopTypo.body14)
            Text(item.price, style = HobbyLoopTypo.head16.copy(color = HobbyLoopColor.Primary))
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(2) { ticket ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF0F0F0), RoundedCornerShape(999.dp))
                            .padding(vertical = 6.dp, horizontal = 12.dp),
                        contentAlignment = Alignment.Center  // This aligns the child to the center of the Box
                    ) {
                        Text(
                            text = "이용권 재구매율 80%",
                            style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBigTicket() {
    BigTicket(
        item = RecommendTicketUiModel(
            isBookMarked = true,
            imageUrl = "",
            location = "서울 강남구",
            category = TicketCategory.PILATES,
            centerName = "필라피티 스튜디오",
            price = "350,000원 ~",
            rating = "4.8",
            reviewCount = "(12)",
        ),
        onBookmarked = {}
    )
}
