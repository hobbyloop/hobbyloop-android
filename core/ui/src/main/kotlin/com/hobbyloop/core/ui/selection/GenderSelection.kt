package com.hobbyloop.core.ui.selection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.signup.componenet.InfoHeadTitle
import com.hobbyloop.ui.R

@Composable
fun GenderSelection(selectedGender: Gender?, selectGender: (Gender) -> Unit) {
    val genders = Gender.entries
    val shape = RoundedCornerShape(8.dp)

    Column {
        InfoHeadTitle(text = stringResource(R.string.signup_gender))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genders.forEach { gender ->
                OutlinedButton(
                    onClick = { selectGender(gender) },
                    shape = shape,
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (selectedGender == gender) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                ) {
                    Text(
                        gender.label,
                        color = if (selectedGender == gender) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

enum class Gender(val label: String) {
    Male("남성"),
    Female("여성")
}

