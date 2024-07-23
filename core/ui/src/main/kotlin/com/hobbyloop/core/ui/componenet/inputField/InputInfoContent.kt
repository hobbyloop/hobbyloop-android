package com.hobbyloop.core.ui.componenet.inputField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.signup.componenet.EnhancedInputField
import com.hobbyloop.feature.signup.componenet.InfoHeadTitle

@Composable
fun InputInfoContent(
    modifier: Modifier = Modifier,
    headerText: String = "",
    hintText: String = "",
    errorText: String = "",
    isEssential: Boolean = true,
    isValid: Boolean,
    value: String,
    valueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        InfoHeadTitle(text = headerText, isEssential = isEssential)
        Spacer(modifier = Modifier.height(8.dp))
        EnhancedInputField(
            modifier = Modifier.fillMaxWidth(),
            hintText = hintText,
            isValid = isValid,
            errorText = errorText,
            value = value,
            valueChange = valueChange,
        )
    }
}
