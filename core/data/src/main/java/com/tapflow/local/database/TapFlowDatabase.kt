package com.tapflow.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tapflow.local.dao.NfcReadHistoryDao
import com.tapflow.local.dao.NfcTagDao
import com.tapflow.local.entity.NfcReadHistoryEntity
import com.tapflow.local.entity.NfcTagEntity

@Database(
    entities = [
        NfcTagEntity::class,
        NfcReadHistoryEntity::class
    ],
    version = 3
)
abstract class TapFlowDatabase : RoomDatabase() {

    abstract fun nfcTagDao(): NfcTagDao

    abstract fun nfcReadHistoryDao(): NfcReadHistoryDao
}