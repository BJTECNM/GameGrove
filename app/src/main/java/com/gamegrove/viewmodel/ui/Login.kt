package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun Login() {

    // var user = ""
    // var password = ""

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a\n GameGrove",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Space()

        Button(
            onClick = { },
            Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, end = 36.dp)
        ) {
            Text(
                text = "Ingresar",
                fontSize = 22.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun Space() {
    Spacer(modifier = Modifier.height(16.dp))
}