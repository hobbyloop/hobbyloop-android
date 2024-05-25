package com.hobbyloop.feature.center.filter.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.center.FilterState
import com.hobbyloop.feature.center.FilterType
import com.hobbyloop.feature.center.regions

@Composable
fun BottomSheetContent(
    selectedFilter: FilterType?,
    filterStateUpdate: (FilterState) -> Unit,
    filterState: FilterState,
    closeBottomSheet: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        when (selectedFilter) {
            FilterType.RATING -> {
                SortBottomSheet(
                    filterStateUpdate,
                    filterState = filterState,
                    closeBottomSheet = closeBottomSheet,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 8.dp),
                )
            }

            FilterType.LOCATION -> {
                RegionBottomSheet(
                    regions = regions,
                    selectedRegions = filterState.location,
                    filterStateUpdate = filterStateUpdate,
                    filterState = filterState,
                    closeBottomSheet = closeBottomSheet
                )
            }

            FilterType.STAR -> {
                ScoreBottomSheet(
                    filterStateUpdate,
                    filterState = filterState,
                    closeBottomSheet = closeBottomSheet,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 8.dp),
                )
            }

            else -> {}
        }
    }
}

