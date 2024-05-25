package com.hobbyloop.feature.center.filter.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.feature.center.FilterState
import com.hobbyloop.feature.center.Region
import com.hobbyloop.feature.center.SelectedRegion
import com.hobbyloop.feature.center.SubRegion
import com.hobbyloop.ui.R
import theme.HobbyLoopColor

@Composable
fun RegionBottomSheet(
    regions: List<Region>,
    filterState: FilterState,
    selectedRegions: List<SelectedRegion>,
    filterStateUpdate: (FilterState) -> Unit,
    closeBottomSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    var filteredRegions by remember { mutableStateOf(regions) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedRegion by remember { mutableStateOf<Region?>(null) }
    var selectedSubRegion by remember { mutableStateOf<SubRegion?>(null) }
    var tempSelectedRegions by remember { mutableStateOf(selectedRegions) }

    fun performSearch() {
        //todo backend
        /*
        filteredRegions = regions.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.subRegions.any { subRegion ->
                        subRegion.name.contains(searchQuery, ignoreCase = true) ||
                                subRegion.areas.any { area ->
                                    area.contains(searchQuery, ignoreCase = true)
                                }
                    }
        }*/
    }

    Column(
        modifier = modifier
            .background(Color.White)
            .wrapContentHeight()
            .fillMaxSize()
    ) {
        Header(closeBottomSheet)
        SearchBar(
            modifier = Modifier.padding(16.dp),
            searchQuery = searchQuery,
            onSearchQueryChanged = { searchQuery = it },
            onSearchTriggered = {
                performSearch()
            }
        )
        SelectionCount(modifier = Modifier.padding(8.dp), tempSelectedRegions.size)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            RegionSelector(
                regions = filteredRegions,
                selectedRegion = selectedRegion,
                onRegionSelected = { region ->
                    selectedRegion = region
                    selectedSubRegion = null
                },
                modifier = Modifier.weight(1f)
            )
            SubRegionSelector(
                selectedRegion = selectedRegion,
                selectedSubRegion = selectedSubRegion,
                onSubRegionSelected = { subRegion -> selectedSubRegion = subRegion },
                modifier = Modifier.weight(1f)
            )
            AreaSelector(
                selectedRegion = selectedRegion,
                selectedSubRegion = selectedSubRegion,
                tempSelectedRegions = tempSelectedRegions,
                onAreaSelected = { selectedRegion ->
                    tempSelectedRegions = if (tempSelectedRegions.contains(selectedRegion)) {
                        tempSelectedRegions - selectedRegion
                    } else if (tempSelectedRegions.size < 10) {
                        tempSelectedRegions + selectedRegion
                    } else {
                        tempSelectedRegions
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
        SelectedRegionsDisplay(tempSelectedRegions) {
            tempSelectedRegions = tempSelectedRegions - it
        }
        BottomActions(
            onConfirm = {
                filterStateUpdate(filterState.copy(location = tempSelectedRegions))
                closeBottomSheet()
            },
            closeBottomSheet = closeBottomSheet
        )
    }
}

@Composable
fun Header(closeBottomSheet: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "지역설정",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Icon(
            painter = painterResource(id = com.hobbyloop.ui.R.drawable.ic_close),
            contentDescription = "Close",
            modifier = Modifier.clickable { closeBottomSheet() }
        )
    }
}

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(50))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChanged,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty() && !isFocused) {
                        Text(
                            text = "지역을 입력해주세요.",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged { isFocused = it.isFocused }
            )
            Icon(
                painter = painterResource(id = com.hobbyloop.ui.R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp)
                    .clickable {
                        keyboardController?.hide()
                        onSearchTriggered()
                    }
            )
        }
    }
}

@Composable
fun SelectionCount(modifier: Modifier = Modifier, count: Int) {
    val text = buildAnnotatedString {
        append("선택지역 (")
        withStyle(style = SpanStyle(color = HobbyLoopColor.SUB, fontWeight = FontWeight.Bold)) {
            append("$count")
        }
        append("/10)")
    }
    Text(
        text = text,
        color = Color.Gray,
        fontSize = 14.sp,
        modifier = modifier
    )
}

@Composable
fun RegionSelector(
    regions: List<Region>,
    selectedRegion: Region?,
    onRegionSelected: (Region) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        com.hobbyloop.feature.center.filter.bottomsheet.SelectorTag(text = "시/도")
        LazyColumn {
            items(regions) { region ->
                com.hobbyloop.feature.center.filter.bottomsheet.LocationItem(
                    text = region.name,
                    isSelect = region == selectedRegion,
                    onClick = { onRegionSelected(region) }
                )
            }
        }
    }
}


@Composable
fun SubRegionSelector(
    selectedRegion: Region?,
    selectedSubRegion: SubRegion?,
    onSubRegionSelected: (SubRegion) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        com.hobbyloop.feature.center.filter.bottomsheet.SelectorTag(text = "구/군/시")
        LazyColumn {
            selectedRegion?.let { region ->
                items(region.subRegions) { subRegion ->
                    com.hobbyloop.feature.center.filter.bottomsheet.LocationItem(
                        text = subRegion.name,
                        isSelect = subRegion == selectedSubRegion,
                        onClick = { onSubRegionSelected(subRegion) }
                    )
                }
            }
        }
    }
}

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelect: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(1.dp, HobbyLoopColor.Gray20)
            .background(if (isSelect) HobbyLoopColor.SUB20 else HobbyLoopColor.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = text,
            fontSize = 14.sp,
            fontWeight = if (isSelect) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelect) HobbyLoopColor.SUB else HobbyLoopColor.Gray80
        )
    }
}

@Composable
fun SelectorTag(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, HobbyLoopColor.Gray40)
            .background(HobbyLoopColor.Gray20),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = text,
            color = HobbyLoopColor.Gray60,
            fontSize = 14.sp
        )
    }
}

@Composable
fun AreaSelector(
    selectedRegion: Region?,
    selectedSubRegion: SubRegion?,
    tempSelectedRegions: List<SelectedRegion>,
    onAreaSelected: (SelectedRegion) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        com.hobbyloop.feature.center.filter.bottomsheet.SelectorTag(text = "동/읍/면")
        LazyColumn {
            selectedSubRegion?.let { subRegion ->
                items(subRegion.areas) { area ->
                    val isSelected =
                        tempSelectedRegions.any { it.region == selectedRegion?.name && it.subRegion == subRegion.name && it.area == area }
                    com.hobbyloop.feature.center.filter.bottomsheet.LocationItem(
                        text = area,
                        isSelect = isSelected,
                        onClick = {
                            selectedRegion?.let {
                                val selected = SelectedRegion(it.name, subRegion.name, area)
                                onAreaSelected(selected)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectedRegionsDisplay(
    tempSelectedRegions: List<SelectedRegion>,
    onRemove: (SelectedRegion) -> Unit
) {
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items(tempSelectedRegions) { selected ->
            Chip(
                text = selected.toString(),
                onRemove = { onRemove(selected) }
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun BottomActions(onConfirm: () -> Unit, closeBottomSheet: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier
                .weight(1f)
                .height(66.dp)
                .padding(end = 4.dp),
            onClick = closeBottomSheet,
            colors = ButtonDefaults.buttonColors(
                containerColor = HobbyLoopColor.Gray20,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "닫기")
        }
        Button(
            modifier = Modifier
                .weight(1f)
                .height(66.dp)
                .padding(start = 4.dp),
            onClick = onConfirm,
            colors = ButtonDefaults.buttonColors(
                containerColor = HobbyLoopColor.Primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "확인")
        }
    }
}


@Composable
fun Chip(
    text: String,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50)
            )
            .border(
                width = 1.dp,
                color = HobbyLoopColor.Primary,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = HobbyLoopColor.Primary,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painterResource(id = R.drawable.ic_close),
            contentDescription = "Remove",
            tint = HobbyLoopColor.Primary,
            modifier = Modifier
                .size(16.dp)
                .clickable { onRemove() }
        )
    }
}

