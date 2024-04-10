package com.hobbyloopapp.feature.facility_card.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.hobbyloopapp.core.ui.facilities.Facility

@Composable
fun CardScreen(
    facility: Facility,
    onBackClick: () -> Unit,
    onCardDetailClick: () -> Unit,
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Home -> Card 화면")
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextColor(label = "시설 ID: ", content = facility.id)
            CustomTextColor(label = "시설 DATA: ", content = facility.data)
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onCardDetailClick,
                content = {
                    Text("CardDetail 화면 이동")
                }
            )
            Button(
                onClick = onBackClick,
                content = {
                    Text("뒤로가기")
                }
            )
        }
    }
}

@Composable
fun CustomTextColor(label: String, content: String) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Red)) {
            append(label)
        }
        withStyle(style = SpanStyle(color = Color.White)) {
            append(content)
        }
    }

    BasicText(text = text)
}
