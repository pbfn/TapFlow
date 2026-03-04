package com.tapflow.usecase

import com.tapflow.NfcTagResult
import com.tapflow.model.NfcTag
import com.tapflow.repository.NfcTagRepository

class HandleNfcTagUseCase(
    private val repository: NfcTagRepository
) {
    suspend fun execute(uid: String): NfcTagResult {

        val existing = repository.getByUid(uid)

        return if (existing == null) {
            repository.save(
                NfcTag(
                    uid = uid,
                    alias = "Nova Tag"
                )
            )
            NfcTagResult.NewTag(uid)
        } else {
            NfcTagResult.ExistingTag(uid, existing.alias)
        }
    }
}