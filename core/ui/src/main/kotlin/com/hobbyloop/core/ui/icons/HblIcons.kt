package com.hobbyloop.core.ui.icons

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.ui.graphics.vector.ImageVector
import com.hobbyloop.core.ui.icons.Icon.*
import com.hobbyloop.ui.R

object HblIcons {

    // login Main Logo
    val AppLogo = DrawableResourceIcon(R.drawable.ic_app_logo)

    val back = DrawableResourceIcon(R.drawable.ic_back)
    val calendar = DrawableResourceIcon(R.drawable.ic_calendar)
    val kakao = DrawableResourceIcon(R.drawable.ic_kakao)
    val google = DrawableResourceIcon(R.drawable.ic_google)
    val naver = DrawableResourceIcon(R.drawable.ic_naver)
}
sealed interface Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon
    data class DrawableResourceIcon(@DrawableRes val resourceId: Int) : Icon
}
