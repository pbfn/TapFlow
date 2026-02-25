package com.tapflow.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tapflow.local.dao.NfcTagDao
import com.tapflow.local.entity.NfcTagEntity

@Database(
    entities = [NfcTagEntity::class],
    version = 1
)
abstract class TapFlowDatabase : RoomDatabase() {

    abstract fun nfcTagDao(): NfcTagDao
}