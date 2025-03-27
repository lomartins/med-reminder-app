package dev.luisamartins.medreminder.data.repository.useregister

import dev.luisamartins.medreminder.data.database.tables.useregister.UseRegisterEntity
import dev.luisamartins.medreminder.domain.model.UseRegister

fun UseRegisterEntity.toModel(): UseRegister {
    return UseRegister(
        id = id,
        scheduleId = scheduleId,
        date = date,
        usageTimeOffsetInMinutes = usageTimeOffsetInMinutes,
    )
}

fun UseRegister.toEntity(): UseRegisterEntity {
    return UseRegisterEntity(
        id = id,
        scheduleId = scheduleId,
        date = date,
        usageTimeOffsetInMinutes = usageTimeOffsetInMinutes,
    )
}