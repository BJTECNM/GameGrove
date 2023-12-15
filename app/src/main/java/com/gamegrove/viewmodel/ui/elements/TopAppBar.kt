package com.gamegrove.viewmodel.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gamegrove.datastore.Preferences
import com.gamegrove.navigation.AppScreens
import com.gamegrove.viewmodel.data.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavHostController, myViewModel: MyViewModel) {
    val context = LocalContext.current
    val preferences = Preferences(context = context)

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Game Grove",
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
            )
        },
        actions = {
            Column(
                modifier = Modifier.padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "LogOut",
                    modifier = Modifier
                        .clickable {
                            myViewModel.signOut()
                            preferences.saveCredential("")
                            navController.popBackStack()
                            navController.navigate(route = AppScreens.Login.route)
                        }
                )
                Text(
                    text = "Cerrar sesión",
                    fontSize = 10.sp
                )
            }
        }
    )
}