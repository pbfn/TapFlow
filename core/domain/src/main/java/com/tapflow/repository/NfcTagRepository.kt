package com.tapflow.repository

import com.tapflow.model.NfcTag

interface NfcTagRepository {
    fun getByUid(uid: String): NfcTag?
    fun save(tag: NfcTag)
}