package com.hobbyloop.core.ui.componenet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.hobbyloop.core.ui.icons.HblIcons
import com.hobbyloop.ui.R


// Default is Back
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int = R.string.empty,
    // left
    navigationIcon: @Composable () -> Unit = { Icon(painter = painterResource(id = HblIcons.back.resourceId), contentDescription = null)},
    // right
    actionIcon: @Composable () -> Unit = {},
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors()
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        navigationIcon = {
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                IconButton(onClick = onNavigationClick) {
                    navigationIcon()
                }
            }
        },
        title = {
            if (titleRes != R.string.empty) {
                Text(text = stringResource(id = titleRes))
            }
        },
        actions = {
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                IconButton(onClick = onActionClick) {
                    actionIcon()
                }
            }
        },
        colors = colors
    )
}

