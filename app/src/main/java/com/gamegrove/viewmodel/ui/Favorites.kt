package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.data.datagames.Game
import com.gamegrove.viewmodel.ui.elements.AlertError
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation
import com.gamegrove.viewmodel.ui.elements.ColumItem
import com.gamegrove.viewmodel.ui.elements.TopAppBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun Favorites(navController: NavHostController, myViewModel: MyViewModel) {
    Scaffold(
        topBar = { TopAppBar(navController, myViewModel) },
        bottomBar = { BottomBarNavigation(navController) }
    ) { innerPaddingValues ->
        ListFavorites(myViewModel, innerPaddingValues)
    }
}

@Composable
fun ListFavorites(myViewModel: MyViewModel, innerPaddingValues: PaddingValues) {
    val favoritesList: List<Game> by myViewModel.favoritesList.observeAsState(listOf())
    val error: String by myViewModel.error.observeAsState(initial = "")
    val db = FirebaseFirestore.getInstance()
    val uid = Firebase.auth.currentUser!!.uid

    myViewModel.getFavoriteList(db, uid)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(favoritesList) {
            ColumItem(myViewModel, it)
        }
        if (favoritesList.isEmpty()) {
            item {
                Text(
                    text = "Agrega juegos a tus favoritos para visualizarlos aquí",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(32.dp),
                    fontSize = 22.sp
                )
            }
        }
    }

    if (error.isNotEmpty()) {
        AlertError(myViewModel = myViewModel, error = error)
    }
}