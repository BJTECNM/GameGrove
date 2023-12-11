package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun VerifyAcount() {
    var user = ""
    var correo = ""
    var code = ""

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Verificar cuenta",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Space()

        OutlinedTextField(
            value = user,
            onValueChange = { },
            label = {
                Text(text = "Usuario: ")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { },
            label = {
                Text(text = "Correo: ")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Space()

        Text(
            text = "Solicitar código",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(4.dp)
                .clickable { /* Acción al pulsar */ }
        )

        Space()

        OutlinedTextField(
            value = code,
            onValueChange = { },
            label = {
                Text(text = "Codigo: ")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )

        Space()

        Button(
            onClick = { },
            Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, end = 36.dp)
        ) {
            Text(
                text = "Restaurar Contraseña",
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}