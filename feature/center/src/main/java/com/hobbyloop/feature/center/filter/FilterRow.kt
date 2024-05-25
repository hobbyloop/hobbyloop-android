package com.hobbyloop.feature.center.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.center.FilterButtonData
import com.hobbyloop.feature.center.FilterState
import com.hobbyloop.feature.center.FilterType
import com.hobbyloop.feature.center.SortingOption
import theme.HobbyLoopColor


@Composable
fun FilterRow(
    modifier: Modifier = Modifier,
    filterState: FilterState,
    onFilterButtonClick: (FilterType) -> Unit
) {
    Row(
        modifier = modifier
            .padding(start = 8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterButtonData.entries.forEach { buttonData ->
            val isSelected = when (buttonData.filterType) {
                FilterType.RATING -> filterState.sort != null
                FilterType.LOCATION -> filterState.location.isNotEmpty()
                FilterType.REFUNDABLE -> filterState.refundable
                FilterType.STAR -> filterState.score != null
            }

            val displayText = when (buttonData.filterType) {
                FilterType.RATING -> filterState.sort?.displayName
                    ?: SortingOption.entries.first().displayName

                FilterType.LOCATION -> if (!isSelected) "위치" else "${filterState.location.first()}외 ${filterState.location.size - 1}개"
                FilterType.REFUNDABLE -> "중도환불 가능"
                FilterType.STAR -> if (!isSelected) "별점" else "${filterState.score?.displayName}"
            }

            val contentColor = if (!isSelected) HobbyLoopColor.Gray100 else HobbyLoopColor.Primary

            FilterButton(
                label = displayText,
                leftIconResId = buttonData.leftIconResId,
                rightIconResId = buttonData.rightIconResId,
                backgroundColor = buttonData.backgroundColor,
                contentColor = contentColor,
                onClick = {
                    onFilterButtonClick(buttonData.filterType)
                }
            )
        }
        Spacer(modifier = Modifier.width(64.dp))
    }
}


@Composable
fun FilterButton(
    label: String,
    modifier: Modifier = Modifier,
    leftIconResId: Int? = null,
    rightIconResId: Int? = null,
    backgroundColor: Color = Color.LightGray,
    onClick: () -> Unit,
    contentColor: Color = HobbyLoopColor.Gray100
) {
    Button(
        modifier = modifier,
        border = BorderStroke(1.dp, HobbyLoopColor.Gray20),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
    ) {
        leftIconResId?.let {
            androidx.compose.material3.Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(label, color = contentColor)
        rightIconResId?.let {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(painter = painterResource(id = it), contentDescription = null, tint = contentColor)
        }
    }
}