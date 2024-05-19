package com.hobbyloop.core.ui.componenet.inputField

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun InfoHeadTitle(
    modifier: Modifier = Modifier,
    text: String,
    isEssential: Boolean = true,
) {
    if (isEssential) {
        val annotatedString = buildAnnotatedString {
            append(text)
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(" *")
            }
        }
        Text(
            modifier = modifier,
            text = annotatedString,
            style = MaterialTheme.typography.titleSmall
        )
    } else {
        Text(modifier = modifier, text = text, style = MaterialTheme.typography.titleSmall)
    }
    Spacer(Modifier.height(8.dp))
}

