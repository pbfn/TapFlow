package com.tapflow.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_action")
data class TagActionEntity(

    @PrimaryKey
    val uid: String,

    val type: String,

    val payload: String
)