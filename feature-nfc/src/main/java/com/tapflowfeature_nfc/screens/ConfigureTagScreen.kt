package com.tapflowfeature_nfc.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapflowfeature_nfc.ConfigureTagViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConfigureTagScreen(
    uid: String,
    viewModel: ConfigureTagViewModel = koinViewModel()
) {

    var url by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Configurar ação da Tag")

        Spacer(Modifier.height(16.dp))

        Text("UID: $uid")

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("URL") }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.saveOpenUrl(uid, url)
            }
        ) {
            Text("Salvar abrir URL")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.saveOpenApp(uid, "com.whatsapp")
            }
        ) {
            Text("Abrir WhatsApp")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.saveWebhook(uid, url)
            }
        ) {
            Text("Salvar Webhook")
        }
    }
}