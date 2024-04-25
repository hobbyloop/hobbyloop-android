package com.hobbyloop.core.ui.componenet.datePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.core.ui.componenet.datePicker.util.Date
import com.hobbyloop.core.ui.componenet.datePicker.util.DateUtils
import com.hobbyloop.core.ui.componenet.datePicker.util.darkPallet
import com.hobbyloop.core.ui.componenet.datePicker.util.daysOfDate
import com.hobbyloop.core.ui.componenet.datePicker.util.lightPallet
import com.hobbyloop.core.ui.componenet.datePicker.util.monthsOfDate
import com.hobbyloop.core.ui.componenet.datePicker.util.withDay
import com.hobbyloop.core.ui.componenet.datePicker.util.withMonth
import com.hobbyloop.core.ui.componenet.datePicker.util.withYear

/**
 * offset : 선택한 아이템 양쪽의 아이템 수를 설정하는 오프셋
 * yearsRange : 선택할 수 있는 연도 범위
 * startDate : 초기 날짜
 * textSize : 텍스트 사이즈
 * selectorEffectEnabled : 선택 효과 활성화 여부
 * onDateChanged : 날짜가 변경될 때 호출할 콜백 함수
 */
@Composable
fun DatePicker(
    offset: Int = 3,
    yearsRange: IntRange = IntRange(1923, 2121),
    startDate: Date = Date(DateUtils.getCurrentTime()),
    textSize: Int = 18,
    selectorEffectEnabled: Boolean = true,
    onDateChanged: (Int, Int, Int, Long) -> Unit = { _, _, _, _ -> },
    darkModeEnabled: Boolean = true,
) {
    // 선택된 날짜 상태
    var selectedDate by remember { mutableStateOf(startDate) }

    val months = selectedDate.monthsOfDate()
    val days = selectedDate.daysOfDate()
    val years = mutableListOf<Int>().apply {
        for (year in yearsRange) {
            add(year)
        }
    }

    // 선택 날짜 변경시 콜백 실행
    LaunchedEffect(selectedDate) {
        onDateChanged(selectedDate.day, selectedDate.month, selectedDate.year, selectedDate.date)
    }

    // 텍스트 크기 설정 범위 내에서 조정
    val fontSize = maxOf(13, minOf(19, textSize))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        val height = (fontSize + 11).dp

        Row(
            modifier = Modifier
                .fillMaxSize()

        ) {

            Spacer(modifier = Modifier.weight(0.5f))
            WheelView(modifier = Modifier
                .weight(1f),
                itemSize = DpSize(150.dp, height),
                selection = years.indexOf(selectedDate.year),
                itemCount = years.size,
                rowOffset = offset,
                isEndless = years.size > offset * 2,
                onFocusItem = {
                    selectedDate = selectedDate.withYear(years[it])
                },
                content = {
                    Text(
                        text = years[it].toString() + "년",
                        textAlign = TextAlign.Start,
                        fontSize = fontSize.sp,
                        style = MaterialTheme.typography.titleSmall
                    )
                })

            WheelView(modifier = Modifier.weight(1f),
                itemSize = DpSize(150.dp, height),
                selection = maxOf(months.indexOf(selectedDate.month), 0),
                itemCount = months.size,
                rowOffset = offset,
                onFocusItem = {
                    selectedDate = selectedDate.withMonth(months[it])
                },
                content = {
                    Text(
                        text = months[it].toString() + "월",
                        textAlign = TextAlign.Start,
                        fontSize = fontSize.sp,
                        style = MaterialTheme.typography.titleSmall,
                    )
                })

            key(days.size) {
                WheelView(modifier = Modifier
                    .weight(1f),
                    itemSize = DpSize(150.dp, height),
                    selection = maxOf(days.indexOf(selectedDate.day), 0),
                    itemCount = days.size,
                    rowOffset = offset,
                    onFocusItem = {
                        selectedDate = selectedDate.withDay(days[it])
                    },
                    content = {
                        Text(
                            style = MaterialTheme.typography.titleSmall,
                            text = days[it].toString() + "일",
                            textAlign = TextAlign.End,
                            fontSize = fontSize.sp,
                        )
                    })
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (darkModeEnabled) lightPallet else darkPallet
                    )
                ),
        ) {}

        SelectorView(darkModeEnabled = darkModeEnabled, offset = offset)
    }
}

