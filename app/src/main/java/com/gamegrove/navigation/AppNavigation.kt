package com.gamegrove.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gamegrove.viewmodel.ui.Home
import com.gamegrove.viewmodel.ui.Login

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.Home.route) {
        composable(route = AppScreens.Home.route) {
            Home() //Home(navController, viewModel)
        }

        composable(route = AppScreens.Login.route) {
            Login()
        }
    }
}