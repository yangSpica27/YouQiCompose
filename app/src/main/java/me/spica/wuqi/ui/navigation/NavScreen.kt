package me.spica.wuqi.ui.navigation

import androidx.compose.runtime.Immutable


/**
 * 页面
 */
@Immutable
sealed class NavScreen(val route: String) {

    // 主页
    object Home : NavScreen("Home")

    // 内容详情页面
    object ContentDetails : NavScreen("Details") {

        const val routeWithArgument: String = "Details/{ContentId}"

        const val argument0: String = "ContentId"
    }

    // 设置页面
    object Setting : NavScreen("Setting")

    // 用户页面
    object UserInfo : NavScreen("UserInfo")

    // 登录页面
    object Login : NavScreen("Login")

}