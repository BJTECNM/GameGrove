package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.data.datagames.Game
import com.gamegrove.viewmodel.ui.elements.AlertError
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun DetailGame(myViewModel: MyViewModel) {
    val game: Game by myViewModel.gameSelected.observeAsState(Game("", 0, 0, "", ""))
    val onFavorites: Boolean by myViewModel.onFavorites.observeAsState(false)
    val error: String by myViewModel.error.observeAsState(initial = "")
    val db = FirebaseFirestore.getInstance()
    val uid = Firebase.auth.currentUser!!.uid

    myViewModel.getFavoriteList(db, uid)
    myViewModel.verifyOnFavorites(game)

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (onFavorites) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.clickable {
                    myViewModel.deleteFavoriteItem(db, uid, game)
                })
        } else {
            Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null,
                modifier = Modifier.clickable {
                    myViewModel.addFavoriteItem(db, uid, game)
                })
        }

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = game.image),
            contentDescription = game.title,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = game.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Lanzamiento: ${game.launch}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Plataformas: ${game.plataform}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Generos: ${game.description}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

    if (error.isNotEmpty()) {
        AlertError(myViewModel = myViewModel, error = error)
    }
}