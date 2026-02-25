package com.tapflow.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nfc_tag")
data class NfcTagEntity(
    @PrimaryKey
    val uid: String,
    val alias: String?
)
