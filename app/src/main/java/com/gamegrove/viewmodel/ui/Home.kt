package com.gamegrove.viewmodel.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun Home() { // Home(navController: NavHostController)
    Scaffold(
        topBar = { News() },
        content = { GamesCatalog() },
        bottomBar = { Menu() }
    )
}

@Composable
fun News() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Próximos Estrenos",
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        // Carrusel de imágenes Horizontal
        /*
        LazyRow() {
            items(estrenos) {
                RowItem(it)
            }
        }
         */
    }
}

@Composable
fun GamesCatalog() {
    /*
    LazyVerticalGrid(columns = GridCells.Fixed(count = 2)) {
        items(games) {
            GridItem(it)
        }
    }
     */
}

@Composable
fun Menu() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Home")
        Text(text = "Favorities")
        Text(text = "Wishes")
    }
}

@Composable
fun RowItem(estreno: Int) {
    Column(Modifier.fillMaxWidth()) {
        AsyncImage(model = null, contentDescription = null)
        Text(text = "Nombre juego")
    }
}

@Composable
fun GridItem(game: Int) {
    Column(Modifier.fillMaxWidth()) {
        AsyncImage(model = null, contentDescription = null)
        Text(text = "Nombre juego")
    }
}