package com.tapflow.mapper

import com.tapflow.local.entity.TagActionEntity
import com.tapflow.model.TagAction
import com.tapflow.model.TagActionType

fun TagActionEntity.toDomain(): TagAction {
    return TagAction(
        uid = uid,
        type = TagActionType.valueOf(type),
        payload = payload
    )
}

fun TagAction.toEntity(): TagActionEntity {
    return TagActionEntity(
        uid = uid,
        type = type.name,
        payload = payload
    )
}