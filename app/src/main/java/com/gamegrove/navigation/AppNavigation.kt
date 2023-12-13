package com.gamegrove.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.ui.Home
import com.gamegrove.viewmodel.ui.Login
import com.gamegrove.viewmodel.ui.SplashScreen

@Composable
fun AppNavigation(myViewModel: MyViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.Home.route) {
            Home(navController, myViewModel)
        }

        composable(route = AppScreens.Login.route) {
            Login(navController, myViewModel)
        }

        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
    }
}