package com.tapflowfeature_nfc

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class NfcDispatchActivity : ComponentActivity() {

    private val viewModel: NfcViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (intent?.action != NfcAdapter.ACTION_TAG_DISCOVERED) return

        val tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: return
        val uid = tagId.joinToString(":") { "%02X".format(it) }

        viewModel.onNfcTag(uid)
        finish()
    }
}