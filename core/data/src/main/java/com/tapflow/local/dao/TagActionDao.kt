package com.tapflow.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tapflow.local.entity.TagActionEntity

@Dao
interface TagActionDao {

    @Query("SELECT * FROM tag_action WHERE uid = :uid LIMIT 1")
    suspend fun getByUid(uid: String): TagActionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(action: TagActionEntity)
}