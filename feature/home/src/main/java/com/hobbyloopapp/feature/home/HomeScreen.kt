package com.hobbyloopapp.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloopapp.core.ui.facilities.Facility

@Composable
fun HomeScreen(
    facilities: List<Facility>,
    onFacilityClick: (facilityId: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Home", color = Color.Red)
        Spacer(modifier = Modifier.height(20.dp))
        FacilityList(
            facilities = facilities,
            onFacilityClick = onFacilityClick
        )
    }
}

@Composable
fun FacilityList(
    facilities: List<Facility>,
    onFacilityClick: (facilityId: String) -> Unit,
) {
    LazyColumn {
        items(facilities.size) { index ->
            val facility = facilities[index]
            Text(
                text = "${facility.id}: ${facility.data}",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onFacilityClick(facility.id)
                    }
            )
        }
    }
}
