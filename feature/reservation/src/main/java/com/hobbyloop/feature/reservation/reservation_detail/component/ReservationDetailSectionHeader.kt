package com.hobbyloop.feature.reservation.reservation_detail.component

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.Gray40
import com.hobbyloop.feature.reservation.Gray60
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.component.box.OutlinedGradientBox
import com.hobbyloop.feature.reservation.component.divider.DottedVerticalDivider

@Composable
fun ClassDetailSectionHeader(
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
                        .width(234.dp)
                        .fillMaxHeight()
                        .padding(start = 24.dp, end = 20.dp)
                        .padding(vertical = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AsyncImage(
                            model = centerIconImageUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = "업체 이미지",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            placeholder = painterResource(id = R.drawable.loading_ic),
                            error = painterResource(id = R.drawable.loading_ic)
                        )

                        Column {
                            Text(
                                text = classTitle,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = centerName,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500),
                                ),
                                color = Gray60,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = instructorName,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500),
                                ),
                                color = Gray60
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    Spacer(
                        modifier = Modifier
                            .width(190.dp)
                            .height(1.dp)
                            .background(Gray20)
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = classTime,
                        style = TextStyle(
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp
                        )
                    )
                }

                DottedVerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = Gray40
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.app_logo_small_ic),
                        contentDescription = "앱 로고 아이콘"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewClassDetailSectionHeader() {
    Surface {
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            ClassDetailSectionHeader(
                centerIconImageUrl = "Brodie",
                classTitle = "6:1 체형교정 필라테스",
                centerName = "필라피티 스튜디오",
                instructorName = "이민주 강사님",
                classTime = "2023. 5. 12 금 09:00 - 09:50",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
            )
        }
    }
}
