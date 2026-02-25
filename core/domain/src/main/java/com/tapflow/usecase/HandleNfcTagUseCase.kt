package com.tapflow.usecase

import com.tapflow.model.NfcTag
import com.tapflow.repository.NfcTagRepository

class HandleNfcTagUseCase(
    private val repository: NfcTagRepository
) {
    fun execute(
        uid: String
    ) {
        val existing = repository.getByUid(uid)

        if (existing == null) {
            repository.save(
                NfcTag(
                    uid = uid,
                    alias = "Nova Tag"
                )
            )
        } else {
            // no futuro: executar perfil
        }
    }
}