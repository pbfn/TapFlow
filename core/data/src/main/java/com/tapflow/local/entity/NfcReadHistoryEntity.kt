package com.tapflow.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nfc_read_history")
data class NfcReadHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val uid: String,
    val timestamp: Long
)