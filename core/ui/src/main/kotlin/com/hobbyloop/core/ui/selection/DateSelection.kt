package com.hobbyloop.core.ui.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.hobbyloop.core.ui.componenet.inputField.EnhancedLeadingIconInputField
import com.hobbyloop.core.ui.componenet.inputField.InfoHeadTitle
import com.hobbyloop.core.ui.icons.HblIcons
import com.hobbyloop.ui.R


@Composable
fun DateSelection(birthDay: String, showSheet: () -> Unit) {
    Column {
        InfoHeadTitle(text = stringResource(id = R.string.signup_birthday))
        EnhancedLeadingIconInputField(
            leadingIcon = HblIcons.calendar.resourceId,
            onClick = showSheet,
            value = birthDay,
            textStyle = MaterialTheme.typography.labelLarge
        )
    }
}