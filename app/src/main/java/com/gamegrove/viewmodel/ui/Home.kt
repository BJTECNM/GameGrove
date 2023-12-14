package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.data.datagames.Games
import com.gamegrove.viewmodel.data.datagames.Premieres
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation
import com.gamegrove.viewmodel.ui.elements.GridItem
import com.gamegrove.viewmodel.ui.elements.RowItem
import com.gamegrove.viewmodel.ui.elements.TopAppBar

@Composable
fun Home(navController: NavHostController, myViewModel: MyViewModel) {
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomBarNavigation(navController) }
    ) { innerPaddingValues ->
        Games(innerPaddingValues)
    }
}

@Composable
fun Games(innerPaddingValues: PaddingValues) {
    val premieres = Premieres.items
    val allGames = Games.items

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Próximos Estrenos",
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        // Carrusel de imágenes Horizontal
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(premieres) {
                RowItem(it)
            }
        }
        // Carrusel de imágenes Vertical
        LazyVerticalGrid(columns = GridCells.Fixed(count = 2)) {
            items(allGames) {
                GridItem(it)
            }
        }
    }
}