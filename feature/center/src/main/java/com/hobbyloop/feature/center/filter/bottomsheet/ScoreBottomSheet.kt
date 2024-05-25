package com.hobbyloop.feature.center.filter.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.feature.center.FilterState
import com.hobbyloop.feature.center.ScoreOption
import com.hobbyloop.ui.R
import theme.HobbyLoopColor


@Composable
fun ScoreBottomSheet(
    filterStateUpdate: (FilterState) -> Unit,
    filterState: FilterState,
    modifier: Modifier = Modifier,
    closeBottomSheet: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "별점",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        ScoreOption.entries.forEach { scoreOption ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (filterState.score == scoreOption) {
                            filterStateUpdate(filterState.copy(score = null))
                        } else {
                            filterStateUpdate(filterState.copy(score = scoreOption))
                        }
                    }
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = scoreOption.displayName,
                    color = if (filterState.score == scoreOption) HobbyLoopColor.Primary else Color.Black,
                    modifier = Modifier.weight(1f)
                )
                if (filterState.score == scoreOption) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "Selected",
                        tint = HobbyLoopColor.Primary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "닫기",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { closeBottomSheet() }
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}
