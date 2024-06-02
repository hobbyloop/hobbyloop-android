package com.hobbyloop.feature.mypage.mypoint.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.mypage.mypoint.model.HistoryUiModel
import com.hobbyloop.member.ui.theme.HobbyLoopTypo
import theme.HobbyLoopColor

@Composable
fun PointHistory(
    modifier: Modifier = Modifier,
    historyList: List<HistoryUiModel>
) {
    PointHistoryList(historyList)
}

@Composable
fun PointHistoryList(historyList: List<HistoryUiModel>) {
        LazyColumn(
            verticalArrangement  = Arrangement.spacedBy(24.dp)
        ) {
            items(historyList) { history ->
                PointHistoryItem(history)
            }
        }

}

@Composable
fun PointHistoryItem(historyUiModel: HistoryUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Row {
            Text(
                text = historyUiModel.date,
                style = HobbyLoopTypo.caption10.copy(color = HobbyLoopColor.Gray40)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "필라피티 스튜디오",
                style = HobbyLoopTypo.head14
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                val pointText = if (historyUiModel.type == 0) {
                    "+${historyUiModel.point}P"
                } else {
                    "-${historyUiModel.point}P"
                }
                Text(
                    text = pointText,
                    style = HobbyLoopTypo.caption12
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${historyUiModel.totalPoint}P",
                    style = HobbyLoopTypo.caption12.copy(color = HobbyLoopColor.Gray60)
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPointHistory() {
    val sampleHistory = listOf(
        HistoryUiModel(
            point = "-20,000",
            type = 1,
            date = "04.10",
            totalPoint = "50,000"
        ),
        HistoryUiModel(
            point = "30,000",
            type = 1,
            date = "04.10",
            totalPoint = "70,000"
        ),
        HistoryUiModel(
            point = "-60,000",
            type = 1,
            date = "04.10",
            totalPoint = "40,000"
        )
    )
    PointHistoryList(historyList = sampleHistory)
}
