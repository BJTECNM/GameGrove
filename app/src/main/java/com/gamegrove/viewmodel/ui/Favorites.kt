package com.gamegrove.viewmodel.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation
import com.gamegrove.viewmodel.ui.elements.TopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Favorites(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomBarNavigation(navController) }
    ) { innerPaddingValues ->

    }
}