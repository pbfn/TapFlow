package com.tapflow.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tapflow.local.entity.NfcTagEntity

@Dao
interface NfcTagDao {

    @Query("SELECT * FROM nfc_tag WHERE uid = :uid LIMIT 1")
    fun getByUid(uid: String): NfcTagEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tag: NfcTagEntity)
}
