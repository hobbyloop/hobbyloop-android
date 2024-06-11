package com.hobbyloop.feature.reservation.reservation_detail.component

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hobbyloop.feature.reservation.Gray20
import com.hobbyloop.feature.reservation.Gray80
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.reservation_detail.component.section.SectionHeader
import com.hobbyloop.feature.reservation.component.box.GradientBox
import com.hobbyloop.feature.reservation.component.button.SurfaceButton

@Composable
fun ClassDetailReservationMethod(
    centerImageUrl: String,
    sessionCount: Int,
    usagePeriod: String,
) {
    var selectedButtonIndex by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SectionHeader(
            title = "예약방법",
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            iconRes = R.drawable.ticket_ic,
            iconPadding = PaddingValues(top = 5.dp, bottom = 5.dp, start = 2.75.dp, end = 3.25.dp)
        )

        Spacer(modifier = Modifier.height(21.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SurfaceButton(
                text = "이용권",
                horizontalPadding = 19.dp,
                verticalPadding = 6.dp,
                fontSize = 12.sp,
                selectable = true,
                selectedTextColor = Purple,
                unselectedTextColor = Gray80,
                selectedBorderColor = Purple,
                unselectedBorderColor = Gray20,
                shape = RoundedCornerShape(999.dp),
                borderWidth = 1.dp,
                isSelected = selectedButtonIndex == 0,
                onClick = {
                    selectedButtonIndex = 0
                }
            )

            SurfaceButton(
                text = "루프패스",
                horizontalPadding = 19.dp,
                verticalPadding = 6.dp,
                fontSize = 12.sp,
                selectable = true,
                selectedTextColor = Purple,
                unselectedTextColor = Gray80,
                selectedBorderColor = Purple,
                unselectedBorderColor = Gray20,
                shape = RoundedCornerShape(999.dp),
                borderWidth = 1.dp,
                isSelected = selectedButtonIndex == 1,
                onClick = {
                    selectedButtonIndex = 1
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        GradientBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(193.dp),
            borderWidth = 2.dp,
            gradientColors = listOf(
                Color(0xFF1A1A1A),  // Dark Gray
                Color(0xFF333333),   // Mid Gray
                Color(0xFF252525),  // Slightly Lighter Gray
            ),
            borderShape = RoundedCornerShape(
                topStart = 55.dp,
                topEnd = 12.dp,
                bottomEnd = 12.dp,
                bottomStart = 55.dp
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                AsyncImage(
                    model = centerImageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = "업체 이미지",
                    modifier = Modifier
                        .size(94.dp),
                    placeholder = painterResource(id = R.drawable.loading_ic),
                    error = painterResource(id = R.drawable.loading_ic)
                )

                Spacer(modifier = Modifier.width(30.dp))

                Column(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "발란스 스튜디오",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.6.dp)
                    ) {
                        Text(
                            text = sessionCount.toString(),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700)
                            )
                        )
                        Text(
                            text = "회",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    }

                    Text(
                        text = usagePeriod,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Preview
@Composable
fun PreviewClassDetailReservationMethod() {
    Surface {
        ClassDetailReservationMethod(
            centerImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA0MTZfMTA0%2FMDAxNTU1NDE1NTAzNTgx.n4hiEiGSF91TMegRtR5o3SA1RZbE6S6SnrnTGNNunlMg.PJoW33HktJZos6K3ENRRpZs3cNdYgSYv_3ph6RzIDx8g.JPEG.bemine9_9%2F0405_2_3.jpg&type=sc960_832",
            sessionCount = 10,
            usagePeriod = "2023.04.20 - 2023.06.20"
        )
    }
}
