package com.hobbyloop.core.ui.icons

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.hobbyloop.core.ui.icons.Icon.*
import com.hobbyloop.ui.R

object HblIcons {

    // login Main Logo
    val AppLogo = DrawableResourceIcon(R.drawable.ic_app_logo)
}
sealed interface Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon
    data class DrawableResourceIcon(@DrawableRes val resourceId: Int) : Icon
}
