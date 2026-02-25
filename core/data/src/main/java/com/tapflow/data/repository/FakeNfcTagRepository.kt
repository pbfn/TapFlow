package com.tapflow.data.repository

import android.util.Log
import com.tapflow.model.NfcTag
import com.tapflow.repository.NfcTagRepository

private const val TAG = "TapFlow-FAKE-DB"
class FakeNfcTagRepository: NfcTagRepository {
    private val tags = mutableMapOf<String, NfcTag>()

    override fun getByUid(uid: String): NfcTag? {
        return tags[uid]
    }

    override fun save(tag: NfcTag) {
        tags[tag.uid] = tag
        Log.d(TAG, "Saved tag: $tag")
    }
}