package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.gamegrove.viewmodel.data.datagames.Games
import com.gamegrove.viewmodel.data.datagames.Premieres
import com.gamegrove.viewmodel.ui.elements.AlertError
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation
import com.gamegrove.viewmodel.ui.elements.GridItem
import com.gamegrove.viewmodel.ui.elements.RowItem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun Home(navController: NavHostController, myViewModel: MyViewModel) {
    Scaffold(
        bottomBar = { BottomBarNavigation(navController) }
    ) { innerPaddingValues ->
        Games(navController, myViewModel, innerPaddingValues)
    }
}

@Composable
fun Games(navController: NavHostController, myViewModel: MyViewModel, innerPaddingValues: PaddingValues) {
    val context = LocalContext.current
    val preferences = Preferences(context = context)
    val premieres = Premieres.items
    val allGames = Games.items
    val error: String by myViewModel.error.observeAsState(initial = "")
    val db = FirebaseFirestore.getInstance()
    val uid = Firebase.auth.currentUser!!.uid

    myViewModel.getFavoriteList(db, uid)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 14.dp, bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = "Próximos Estrenos",
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                fontSize = 22.sp
            )
            Column(
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
        // Carrusel de imágenes Horizontal
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(premieres) {
                RowItem(navController, myViewModel, it)
            }
        }
        // Carrusel de imágenes Vertical
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            columns = GridCells.Fixed(count = 2)
        ) {
            items(allGames) {
                GridItem(navController, myViewModel, it)
            }
        }
    }

    if (error.isNotEmpty()) {
        AlertError(myViewModel = myViewModel, error = error)
    }
}