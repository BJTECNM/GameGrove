package com.gamegrove.navigation

sealed class AppScreens(val route: String) {
    object Home : AppScreens("home")

    object Login : AppScreens("login")
}