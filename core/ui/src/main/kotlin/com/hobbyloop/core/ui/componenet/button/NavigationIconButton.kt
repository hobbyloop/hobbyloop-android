package com.hobbyloop.core.ui.componenet.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hobbyloop.ui.R
import theme.HobbyLoopColor.Gray100
import theme.HobbyLoopColor.Gray40

@Composable
fun NavigationIconButton(
    isEnabled: Boolean,
    iconId: Int,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabledColor: Color = Gray100,
    disabledColor: Color = Gray40,
) {
    IconButton(
        onClick = { if (isEnabled) onClick() },
        enabled = isEnabled,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = description,
            tint = if (isEnabled) enabledColor else disabledColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNavigationIconButton() {
    NavigationIconButton(
        isEnabled = true,
        iconId = R.drawable.ic_back,
        description = "Play",
        onClick = { },
        enabledColor = Color.Blue,
        disabledColor = Color.Gray
    )
}
