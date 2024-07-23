package com.hobbyloop.feature.signup.componenet

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.hobbyloop.core.ui.componenet.SingleLineText

@Composable
fun EnhancedInputField(
    modifier: Modifier = Modifier,
    hintText: String,
    isValid: Boolean,
    errorText: String,
    value: String,
    valueChange: (String) -> Unit,
    isPhoneNumber: Boolean = false,
    textStyle : TextStyle = MaterialTheme.typography.titleSmall,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()
    val borderColor = if (!isValid && value.isNotBlank()) MaterialTheme.colorScheme.error
    else if (isFocused.value) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onBackground

    if (isPhoneNumber) {
        LaunchedEffect(key1 = isValid) {
            if (isValid) keyboardController?.hide()
        }
    }

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                .height(48.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = {
                    val newValue =
                        if (isPhoneNumber) it.filter { c -> c.isDigit() }.take(11) else it
                    valueChange(newValue)
                },
                singleLine = true,
                textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onTertiary),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                visualTransformation = if (isPhoneNumber) PhoneNumberVisualTransformation else VisualTransformation.None,
                interactionSource = interactionSource,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) { innerTextField ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    innerTextField()
                    if (value.isEmpty()) {
                        SingleLineText(
                            hintText,
                            shouldUseMarquee = true,
                            style = textStyle,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        if (!isValid && value.isNotBlank()) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun EnhancedLeadingIconInputField(
    modifier: Modifier = Modifier,
    hintText: String = "",
    value: String,
    @DrawableRes leadingIcon: Int,
    textStyle : TextStyle = MaterialTheme.typography.titleSmall,
    onClick: () -> Unit = {}
) {
    Column(modifier = modifier.clickable(onClick = onClick))
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(8.dp))
                .height(48.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                BasicTextField(
                    readOnly = true,
                    enabled = false,
                    value = value,
                    onValueChange = {},
                    singleLine = true,
                    textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onTertiary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        innerTextField()
                        if (value.isEmpty()) {
                            SingleLineText(
                                hintText,
                                shouldUseMarquee = true,
                                style = textStyle,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                IconButton(
                    onClick = onClick,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = "Leading Icon"
                    )
                }
            }
        }
    }
}

object PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }
        val formatted = when {
            digits.length >= 12 -> "${digits.substring(0, 3)}-${
                digits.substring(
                    3,
                    7
                )
            }-${digits.substring(7, 11)}"

            digits.length >= 8 -> "${digits.substring(0, 3)}-${
                digits.substring(
                    3,
                    7
                )
            }-${digits.substring(7)}"

            digits.length >= 4 -> "${digits.substring(0, 3)}-${digits.substring(3)}"
            else -> digits
        }
        return TransformedText(
            AnnotatedString(formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset <= 3 -> offset
                        offset <= 7 -> offset + 1
                        offset <= 11 -> offset + 2
                        else -> offset + 3
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 3 -> offset
                        offset <= 8 -> offset - 1
                        offset <= 13 -> offset - 2
                        else -> offset - 3
                    }
                }
            }
        )
    }
}
