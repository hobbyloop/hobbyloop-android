package com.hobbyloopapp.feature.my_page

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.hobbyloopapp.core.ui.ROOT_DEEPLINK

const val MYPAGE_GRAPH_ROUTE = "mypage-graph"
private const val MYPAGE_DEEPLINK = "$ROOT_DEEPLINK/mypage"

fun NavGraphBuilder.mypageGraph(
) {
    navigation(
        startDestination = MYPAGE_ROUTE,
        route = MYPAGE_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = MYPAGE_DEEPLINK }
        ),
    ) {
        mypageScreen()
    }
}
