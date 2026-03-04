package com.example.tapflow

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tapflow.ui.theme.TapFlowTheme
import com.tapflowfeature_nfc.mvi.NfcIntent
import com.tapflowfeature_nfc.mvi.NfcViewModel
import com.tapflowfeature_nfc.screens.NfcScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: NfcViewModel by viewModel()
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_MUTABLE
        )

        enableEdgeToEdge()
        setContent {
            TapFlowTheme {
                NfcScreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter.enableForegroundDispatch(
            this,
            pendingIntent,
            null,
            null
        )
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent?.action != NfcAdapter.ACTION_TAG_DISCOVERED) return

        val tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: return
        val uid = tagId.joinToString(":") { "%02X".format(it) }


        viewModel.handleIntent(NfcIntent.TagScanned(uid))
    }
}