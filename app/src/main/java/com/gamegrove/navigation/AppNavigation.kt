package com.gamegrove.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gamegrove.viewmodel.ui.Home
import com.gamegrove.viewmodel.ui.Login
import com.gamegrove.viewmodel.ui.Register
import com.gamegrove.viewmodel.ui.RestorePassword
import com.gamegrove.viewmodel.ui.VerifyAcount

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

        composable(route = AppScreens.Register.route) {
            Register()
        }

        composable(route = AppScreens.VerifyAcount.route) {
            VerifyAcount()
        }

        composable(route = AppScreens.RestorePassword.route) {
            RestorePassword()
        }
    }
}