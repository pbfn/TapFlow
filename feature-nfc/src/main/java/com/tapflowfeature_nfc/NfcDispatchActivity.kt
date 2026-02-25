package com.tapflowfeature_nfc

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class NfcDispatchActivity : AppCompatActivity() {

    private val viewModel: NfcViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent = intent)
        finish()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
        finish()
    }

    private fun handleIntent(intent: Intent?) {
        if (intent?.action != NfcAdapter.ACTION_TAG_DISCOVERED) return

        val tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: return

        val uid = tagId.joinToString(":") { byte ->
            "%02X".format(byte)
        }

        viewModel.onNfcTag(uid)
    }
}