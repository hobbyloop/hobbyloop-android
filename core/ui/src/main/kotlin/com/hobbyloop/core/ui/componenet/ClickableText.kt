package com.hobbyloop.core.ui.componenet

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun UnderLineClickableText(text : String , style : TextStyle = MaterialTheme.typography.labelLarge, textColor : Color = MaterialTheme.colorScheme.onBackground){
    ClickableText(
        text = AnnotatedString(
            "자세히", spanStyle = SpanStyle(
                color = textColor,
                textDecoration = TextDecoration.Underline
            )
        ),
        onClick = { },
        style =style
    )
}