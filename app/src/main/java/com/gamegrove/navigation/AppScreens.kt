package com.gamegrove.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splashscreen")
    object Home : AppScreens("home")
    object Login : AppScreens("login")
}