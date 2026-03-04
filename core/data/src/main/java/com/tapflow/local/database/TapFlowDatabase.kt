package com.tapflow.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tapflow.local.dao.NfcReadHistoryDao
import com.tapflow.local.dao.NfcTagDao
import com.tapflow.local.dao.TagActionDao
import com.tapflow.local.entity.NfcReadHistoryEntity
import com.tapflow.local.entity.NfcTagEntity
import com.tapflow.local.entity.TagActionEntity

@Database(
    entities = [
        NfcTagEntity::class,
        NfcReadHistoryEntity::class,
        TagActionEntity::class
    ],
    version = 4
)
abstract class TapFlowDatabase : RoomDatabase() {

    abstract fun nfcTagDao(): NfcTagDao
    abstract fun nfcReadHistoryDao(): NfcReadHistoryDao
    abstract fun tagActionDao(): TagActionDao
}