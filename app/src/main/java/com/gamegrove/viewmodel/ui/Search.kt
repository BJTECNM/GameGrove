package com.gamegrove.viewmodel.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Search(navController: NavHostController) {
    Scaffold(
        topBar = {  },
        content = {  },
        bottomBar = { BottomBarNavigation(navController) }
    )
}