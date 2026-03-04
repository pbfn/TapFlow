package com.tapflow.model

data class TagAction(
    val uid: String,
    val type: TagActionType,
    val payload: String
)