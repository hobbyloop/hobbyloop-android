package com.hobbyloop.core.ui.componenet.reservation.section

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.ui.R

@Composable
fun SectionHeader(
    title: String,
    textStyle: TextStyle,
    @DrawableRes iconRes: Int,
    isIconTintEnabled: Boolean = false,
    iconTint: Color = Color.Unspecified,
    iconDescription: String = "",
    iconPadding: PaddingValues = PaddingValues(0.dp)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(iconPadding),
            painter = painterResource(id = iconRes),
            contentDescription = iconDescription,
            tint = if (isIconTintEnabled) iconTint else Color.Unspecified
        )
        Text(
            text = title,
            style = textStyle
        )
    }
}


@Preview
@Composable
fun PreviewSectionHeader() {
    Surface {
        SectionHeader(
            title = "예약방법",
            iconRes = R.drawable.ic_ticket,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
            ),
            iconDescription = "예약 아이콘",
            iconPadding = PaddingValues(top = 4.dp, bottom = 4.dp, start = 2.75.dp, end = 3.25.dp)
        )
    }
}
