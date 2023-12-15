package com.gamegrove.viewmodel.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation
import com.gamegrove.viewmodel.ui.elements.TopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Favorites(navController: NavHostController, myViewModel: MyViewModel) {
    Scaffold(
        topBar = { TopAppBar(navController, myViewModel) },
        bottomBar = { BottomBarNavigation(navController) }
    ) { innerPaddingValues ->

    }
}