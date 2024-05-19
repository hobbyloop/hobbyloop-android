package com.hobbyloop.feature.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.hobbyloop.feature.mypage.editmyinfo.editMyInfoScreen
import com.hobbyloop.feature.mypage.editmyinfo.navigateToEditMyInfo
import com.hobbyloop.feature.mypage.mybookmark.myBookmarkScreen
import com.hobbyloop.feature.mypage.mybookmark.navigateToMyBookmark
import com.hobbyloop.feature.mypage.myclass.myClassScreen
import com.hobbyloop.feature.mypage.myclass.navigateToMyClass
import com.hobbyloop.feature.mypage.myclassdetail.myClassDetailScreen
import com.hobbyloop.feature.mypage.myclassdetail.navigateToMyClassDetail
import com.hobbyloop.feature.mypage.mycoupon.myCouponScreen
import com.hobbyloop.feature.mypage.mycoupon.navigateToMyCoupon
import com.hobbyloop.feature.mypage.mypoint.myPointScreen
import com.hobbyloop.feature.mypage.mypoint.navigateToMyPoint
import com.hobbyloop.feature.mypage.myticket.myTicketScreen
import com.hobbyloop.feature.mypage.myticket.navigateToMyTicket
import com.hobbyloop.feature.mypage.setting.mySettingScreen
import com.hobbyloop.feature.mypage.setting.navigateToMySetting

const val MY_PAGE_GRAPH_ROUTE = "my-page-graph"

fun NavGraphBuilder.myPageGraph(navController: NavController) {
    navigation(
        startDestination = MY_PAGE_ROUTE,
        route = MY_PAGE_GRAPH_ROUTE,
    ) {
        myPageScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onSettingClick = {
                navController.navigateToMySetting()
            },
            onEditMyInfoClick = {
                navController.navigateToEditMyInfo()
            },
            onMyPointClick = {
                navController.navigateToMyPoint()
            },
            onMyTicketClick = {
                navController.navigateToMyTicket()
            },
            onMyCouponClick = {
                navController.navigateToMyCoupon()
            },
            onMyBookmarkClick = {
                navController.navigateToMyBookmark()
            },
            onMyClassClick = {
                navController.navigateToMyClass()
            },
        )
        mySettingScreen(
            onCloseClick = {
                navController.popBackStack()
            }
        )
        editMyInfoScreen(
            onCloseClick = {
                navController.popBackStack()
            }
        )
        myPointScreen(
            onCloseClick = {
                navController.popBackStack()
            }
        )
        myTicketScreen(
            onCloseClick = {
                navController.popBackStack()
            }
        )
        myCouponScreen(
            onCloseClick = {
                navController.popBackStack()
            }
        )
        myBookmarkScreen(
            onCloseClick = {
                navController.popBackStack()
            }
        )
        myClassScreen(
            onCloseClick = {
                navController.popBackStack()
            },
            onMyClassDetail = {
                navController.navigateToMyClassDetail()
            },
        )
        myClassDetailScreen(
            onCloseClick = {
                navController.popBackStack()
            }
        )
    }
}
