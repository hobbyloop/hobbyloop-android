package com.hobbyloop.feature.mypage

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val MY_PAGE_GRAPH_ROUTE = "my-page-graph"

fun NavGraphBuilder.myPageGraph(
    onContentColor: (color: Color) -> Unit,
) {
    navigation(
        startDestination = MY_PAGE_ROUTE,
        route = MY_PAGE_GRAPH_ROUTE,
    ) {
        myPageScreen(onContentColor = onContentColor)
    }
}
