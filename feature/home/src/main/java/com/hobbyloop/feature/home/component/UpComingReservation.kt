package com.hobbyloop.feature.home.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.home.model.UpcomingReservationUiModel
import theme.HobbyLoopColor
import theme.HobbyLoopTypo

@Composable
fun UpComingReservation(
    modifier: Modifier = Modifier,
    item : UpcomingReservationUiModel,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                width = 2.dp, brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF9EB6FC), Color(0xFFFFAAAA)),
                ), shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 10.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 40.dp
                )
            )
            .background(color = HobbyLoopColor.White,
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 10.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 40.dp
                )
            )
            .padding(horizontal = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .border(width = 1.dp, shape = CircleShape, color = Color.Black),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Row {
                        Column {
                            Text(
                                item.className,
                                style = HobbyLoopTypo.body14
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                item.centerName,
                                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                item.teacherName,
                                style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60)
                            )
                        }
                    }

                }
                Divider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .width(220.dp),
                    thickness = 1.dp,
                    color = Color(0xFFF4F4F4)
                )
                Text(
                    text = item.classPeriod,
                    style = HobbyLoopTypo.head16
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            VerticalDashedDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Box(
                modifier = Modifier
                    .clickable {
                        Log.d("", "QR입력")
                    }
                    .size(68.dp)
                    .border(
                        width = 1.dp,
                        shape = RectangleShape,
                        color = Color.Black,
                    ),
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun PreviewUpComingReservation() {
    UpComingReservation(
        modifier = Modifier.padding(horizontal = 16.dp),
        item = UpcomingReservationUiModel(
            className = "6:1 체형교정 필라테스",
            centerName = "필라피티 스튜디오",
            teacherName = "이민주 강사님",
            classPeriod = "2023. 5. 12 금 09:00 - 09:50",
            qr = "",
            centerLogoUrl = ""
        )
    )
}
