package com.gamegrove.viewmodel.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.data.datagames.Game
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun ColumItem(myViewModel: MyViewModel, game: Game) {
    val db = FirebaseFirestore.getInstance()
    val uid = Firebase.auth.currentUser!!.uid

    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(id = game.image),
                contentDescription = game.title,
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Icon(
                modifier = Modifier
                    .clickable {
                        myViewModel.deleteFavoriteItem(db, uid, game)
                        myViewModel.getFavoriteList(db, uid)
                    },
                imageVector = Icons.Filled.Favorite,
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = game.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Lanzamiento: ${game.launch}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Plataformas: ${game.plataform}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Generos: ${game.genre}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}