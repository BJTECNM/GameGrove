package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.gamegrove.R
import com.gamegrove.datastore.Preferences
import com.gamegrove.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val preferences = Preferences(context = context)

    LaunchedEffect(key1 = true) {
        delay(2000)
        if (preferences.getCredential() == "") {
            navController.popBackStack()
            navController.navigate(route = AppScreens.Login.route)
        } else {
            navController.popBackStack()
            navController.navigate(route = AppScreens.Home.route)
        }
    }

    Splash()
}

@Composable
fun Splash() {
    Image(
        painter = painterResource(id = R.drawable.splash),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}