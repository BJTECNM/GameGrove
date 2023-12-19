package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    val onFavorites: Boolean by myViewModel.onFavorites.observeAsState(initial = false)
    val error: String by myViewModel.error.observeAsState(initial = "")
    val db = FirebaseFirestore.getInstance()
    val uid = Firebase.auth.currentUser!!.uid

    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Detalles del juego"
            )
        },
        title = {
            Text(
                text = game.title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = game.image),
                    contentDescription = game.title,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Lanzamiento: ${game.launch}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Plataformas: ${game.plataform}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Generos: ${game.genre}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (onFavorites) {
                    Row(
                        Modifier
                            .clickable {
                                myViewModel.deleteFavoriteItem(db, uid, game)
                                myViewModel.getFavoriteList(db, uid)
                            },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Eliminar de favoritos",
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null
                        )
                    }
                } else {
                    Row(
                        Modifier
                            .clickable {
                                myViewModel.addFavoriteItem(db, uid, game)
                                myViewModel.getFavoriteList(db, uid)
                            },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Añadir a favoritos",
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { myViewModel.deselectGame() }) {
                Text(
                    text = "Aceptar",
                    textAlign = TextAlign.Center
                )
            }
        },
        onDismissRequest = { myViewModel.deselectGame() }
    )

    if (error.isNotEmpty()) {
        AlertError(myViewModel = myViewModel, error = error)
    }
}