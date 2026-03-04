package com.tapflowfeature_nfc.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tapflowfeature_nfc.NfcUiState
import com.tapflowfeature_nfc.NfcViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NfcScreen(
    viewModel: NfcViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val history by viewModel.history.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = {
            viewModel.onNfcTag("04:A2:9F:1C:88")
        }) {
            Text("Simular NFC")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is NfcUiState.Loading -> {
                Text("Processando NFC...")
            }
            is NfcUiState.NewTag -> {
                Text("Nova tag detectada!")
            }
            is NfcUiState.KnownTag -> {
                Text("Tag conhecida!")
            }
            is NfcUiState.Error -> {
                Text("Erro ao ler tag")
            }
            else -> Unit
        }

        Spacer(modifier = Modifier.height(24.dp))

        history.forEach {
            Text("UID: ${it.uid}")
        }
    }
}