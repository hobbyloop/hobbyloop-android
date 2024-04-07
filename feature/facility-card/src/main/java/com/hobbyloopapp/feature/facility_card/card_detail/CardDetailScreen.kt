package com.hobbyloopapp.feature.facility_card.card_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hobbyloopapp.core.ui.facilities.Facility
import com.hobbyloopapp.feature.facility_card.card.CustomTextColor

@Composable
fun CardDetailScreen(
    facility: Facility,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home -> Card -> CardDetail 화면 / dialog 화면")
        Spacer(modifier = Modifier.height(10.dp))
        CustomTextColor(label = "시설 ID: ", content = facility.id)
        CustomTextColor(label = "시설 DATA: ", content = facility.data)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onBackClick,
            content = {
                Text("뒤로가기")
            }
        )
    }
}
