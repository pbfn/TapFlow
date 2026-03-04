package com.tapflow.mapper

import com.tapflow.local.entity.NfcReadHistoryEntity
import com.tapflow.model.NfcReadHistory

fun NfcReadHistoryEntity.toDomain() =
    NfcReadHistory(id, uid, timestamp)

fun NfcReadHistory.toEntity() =
    NfcReadHistoryEntity(id, uid, timestamp)