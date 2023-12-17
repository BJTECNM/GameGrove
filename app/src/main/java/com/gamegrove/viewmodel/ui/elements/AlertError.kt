package com.gamegrove.viewmodel.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.gamegrove.viewmodel.data.MyViewModel

@Composable
fun AlertError(myViewModel: MyViewModel, error: String) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Ha ocurrido un error"
            )
        },
        title = { Text(text = "Ha ocurrido un error") },
        text = { Text(text = error) },
        confirmButton = {
            TextButton(onClick = { myViewModel.restoreErrorState() }) {
                Text(
                    text = "Aceptar",
                    textAlign = TextAlign.Center
                )
            }
        },
        onDismissRequest = { myViewModel.restoreErrorState() }
    )
}