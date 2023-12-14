package com.gamegrove.viewmodel.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gamegrove.navigation.AppScreens

@Composable
fun BottomBarNavigation(navController: NavHostController) {
    val currentRoute = currentRoute(navController = navController)

    BottomAppBar {
        NavigationBarItem(
            selected = currentRoute == AppScreens.Home.route,
            onClick = {
                if (currentRoute != AppScreens.Home.route) {
                    navController.popBackStack()
                    navController.navigate(route = AppScreens.Home.route)
                }
            },
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Inicio") },
            label = { Text(text = "Inicio") },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = currentRoute == AppScreens.Search.route,
            onClick = {
                if (currentRoute != AppScreens.Search.route) {
                    navController.popBackStack()
                    navController.navigate(route = AppScreens.Search.route)
                }
            },
            icon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Busqueda") },
            label = { Text(text = "Inicio") },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = currentRoute == AppScreens.Favorites.route,
            onClick = {
                if (currentRoute != AppScreens.Favorites.route) {
                    navController.popBackStack()
                    navController.navigate(route = AppScreens.Favorites.route)
                }
            },
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favoritos") },
            label = { Text(text = "Inicio") },
            alwaysShowLabel = false
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}