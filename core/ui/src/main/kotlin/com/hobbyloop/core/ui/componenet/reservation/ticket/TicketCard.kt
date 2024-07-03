package com.hobbyloop.core.ui.componenet.reservation.ticket

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hobbyloop.core.ui.componenet.box.OutlinedGradientBox
import com.hobbyloop.core.ui.componenet.divider.DottedVerticalDivider
import com.hobbyloop.ui.R
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun TicketCard(
    centerIconImageUrl: String,
    classTitle: String,
    centerName: String,
    instructorName: String,
    classTime: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedGradientBox(
            modifier = Modifier
                .width(358.dp)
                .fillMaxHeight(),
            borderWidth = 2.dp,
            gradientColors = listOf(
                Color(0xFF9EB6FC),
                Color(0xFFFFAAAA)
            ),
            borderShape = RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 10.dp,
                bottomEnd = 10.dp,
                bottomStart = 40.dp
            ),
            unSelectedColor = Color.White,
            onClick = { }
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .width(245.dp)
                        .fillMaxHeight()
                        .padding(start = 24.dp, end = 20.dp)
                        .padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = centerIconImageUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = "업체 이미지",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            placeholder = painterResource(id = R.drawable.ic_close),
                            error = painterResource(id = R.drawable.ic_close)
                        )

                        Column {
                            Text(
                                text = classTitle,
                                style = HobbyLoopTypo.body14,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = centerName,
                                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "$instructorName 강사님",
                                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(HobbyLoopColor.Gray20)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = classTime,
                        style = HobbyLoopTypo.head14.copy(fontSize = 14.6.sp, lineHeight = 14.6.sp)
                    )
                }

                DottedVerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = HobbyLoopColor.Gray40
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_app_logo_small),
                        tint = Color.Unspecified,
                        contentDescription = "앱 로고 아이콘"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTicketCard() {
    Surface {
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            TicketCard(
                centerIconImageUrl = "Brodie",
                classTitle = "6:1 체형교정 필라테스",
                centerName = "필라피티 스튜디오",
                instructorName = "이민주 강사님",
                classTime = "2023. 5. 12 금 09:00 - 09:50",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
            )
        }
    }
}
