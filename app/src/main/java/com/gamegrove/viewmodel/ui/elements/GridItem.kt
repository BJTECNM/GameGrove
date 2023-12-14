package com.gamegrove.viewmodel.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.gamegrove.viewmodel.data.datagames.Game

@Composable
fun GridItem(game: Game) {
    Column(Modifier.fillMaxWidth()) {
        AsyncImage(model = game.image, contentDescription = game.title)
        Text(text = game.title)
    }
}