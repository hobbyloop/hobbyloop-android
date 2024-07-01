package com.hobbyloop.core.ui.icons

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.hobbyloop.core.ui.icons.Icon.*
import com.hobbyloop.ui.R

object HblIcons {

    // login
    val AppLogo = DrawableResourceIcon(R.drawable.ic_app_logo)
    val kakao = DrawableResourceIcon(R.drawable.ic_kakao)
    val google = DrawableResourceIcon(R.drawable.ic_google)
    val naver = DrawableResourceIcon(R.drawable.ic_naver)



    val unCheck = DrawableResourceIcon(R.drawable.ic_uncheck)
    val check = DrawableResourceIcon(R.drawable.ic_check_box_on_color)

    val back = DrawableResourceIcon(R.drawable.ic_back)
    val calendar = DrawableResourceIcon(R.drawable.ic_calendar)






}
sealed interface Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon
    data class DrawableResourceIcon(@DrawableRes val resourceId: Int) : Icon
}
