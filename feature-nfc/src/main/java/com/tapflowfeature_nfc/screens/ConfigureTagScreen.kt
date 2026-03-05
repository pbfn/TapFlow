package com.tapflowfeature_nfc.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfigureTagScreen(
    uid: String
) {

    Column {

        Text("Configurar Tag")

        Text("UID: $uid")

        Button(
            onClick = { }
        ) {
            Text("Abrir App")
        }

        Button(
            onClick = { }
        ) {
            Text("Abrir URL")
        }

        Button(
            onClick = { }
        ) {
            Text("Webhook")
        }
    }
}