package com.tapflow.mapper

import com.tapflow.local.entity.NfcTagEntity
import com.tapflow.model.NfcTag

fun NfcTagEntity.toDomain(): NfcTag {
    return NfcTag(
        uid = uid,
        alias = alias
    )
}

fun NfcTag.toEntity(): NfcTagEntity {
    return NfcTagEntity(
        uid = uid,
        alias = alias
    )
}