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
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.data.datagames.Game
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation
import com.gamegrove.viewmodel.ui.elements.ColumItem
import com.gamegrove.viewmodel.ui.elements.TopAppBar

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

    myViewModel.getFavoriteList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(favoritesList) {
            ColumItem(it)
        }
        if (favoritesList.isEmpty()) {
            item {
                Text(
                    text = "Agrega juegos a tus favoritos para visualizarlos aquí",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}