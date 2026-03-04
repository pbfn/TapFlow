package com.tapflowfeature_nfc.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tapflowfeature_nfc.NfcStatus
import com.tapflowfeature_nfc.mvi.NfcEvent
import com.tapflowfeature_nfc.mvi.NfcIntent
import com.tapflowfeature_nfc.mvi.NfcViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun NfcScreen(
    viewModel: NfcViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is NfcEvent.ShowToast ->
                    Toast.makeText(
                        context,
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()

                NfcEvent.NavigateToConfig -> {
                    // futura navegação
                }
            }
        }
    }

    LaunchedEffect(state.status) {

        if (state.status is NfcStatus.NewTag) {

            delay(2000)

            viewModel.handleIntent(
                NfcIntent.ResetStatus
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (val status = state.status) {
            is NfcStatus.Loading -> Text("Processando...")
            is NfcStatus.NewTag -> Text("Nova tag!")
            is NfcStatus.KnownTag -> Text("Tag conhecida: ${status.alias}")
            is NfcStatus.Error -> Text("Erro: ${status.message}")
            NfcStatus.Idle -> Unit
        }

        Spacer(modifier = Modifier.height(16.dp))

        state.history.forEach {
            Text("UID: ${it.uid}")
        }
    }
}