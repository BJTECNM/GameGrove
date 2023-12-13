package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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

@Preview(showSystemUi = true)
@Composable
fun Splash() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GameGrove",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
    }
}