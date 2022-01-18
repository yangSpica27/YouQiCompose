package me.spica.wuqi.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import me.spica.wuqi.ui.detail.ContentDetailsScreen
import me.spica.wuqi.ui.home.HomeScreen
import me.spica.wuqi.ui.login.LoginScreen
import me.spica.wuqi.ui.navigation.NavScreen
import me.spica.wuqi.ui.setting.SettingScreen
import me.spica.wuqi.ui.user.UserScreen


/**
 * 主页
 */
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {


        NavHost(
            navController = navController,
            startDestination = NavScreen.Home.route,
        ) {

            // 主页
            composable(NavScreen.Home.route) {
                HomeScreen(navController = navController)
            }

            // 详情页面
            composable(
                NavScreen.ContentDetails.routeWithArgument,
                arguments = listOf(
                    navArgument(NavScreen.ContentDetails.argument0) {
                        type = NavType.LongType
                    }
                )
            ) {
                val contentId = it.arguments?.getInt(NavScreen.ContentDetails.argument0)
                ContentDetailsScreen(contentId = contentId ?: 0)
            }

            // 设置页面
            composable(
                route = NavScreen.Setting.route,
            ) {
                SettingScreen()
            }



            // 用户信息页面
            composable(NavScreen.UserInfo.route) {
                UserScreen()
            }

            //  登录页面
            composable(NavScreen.Login.route) {
                LoginScreen()
            }
        }
    }
}


