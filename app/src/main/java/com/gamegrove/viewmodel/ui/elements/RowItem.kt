package com.gamegrove.viewmodel.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.data.datagames.Game
import com.gamegrove.viewmodel.ui.DetailGame

@Composable
fun RowItem(navController: NavHostController, myViewModel: MyViewModel, game: Game) {
    val isSelected: Boolean by myViewModel.isSelected.observeAsState(initial = false)

    Column(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    myViewModel.selectGame(game)
                },
            painter = painterResource(id = game.image),
            contentDescription = game.title,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = game.title,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }

    if (isSelected) {
        DetailGame(myViewModel = myViewModel)
    }
}