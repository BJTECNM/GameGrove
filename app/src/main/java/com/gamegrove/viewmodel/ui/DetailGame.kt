package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gamegrove.viewmodel.data.datagames.Game
import com.gamegrove.viewmodel.ui.elements.Space

@Composable
fun DetailGame(game: Game) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = game.image),
            contentDescription = game.title,
            modifier = Modifier.height(250.dp),
            contentScale = ContentScale.Fit
        )

        Space()

        Text(text = game.title)

        Space()

        Text(text = game.launch.toString())

        Space()

        Text(text = game.plataform)

        Space()

        Text(text = game.description)
    }
}