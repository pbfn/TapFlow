package com.tapflow.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tapflow.local.entity.NfcReadHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NfcReadHistoryDao {

    @Insert
    suspend fun insert(history: NfcReadHistoryEntity)

    @Query("SELECT * FROM nfc_read_history ORDER BY timestamp DESC")
    fun observeAll(): Flow<List<NfcReadHistoryEntity>>
}