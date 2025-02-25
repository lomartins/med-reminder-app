package dev.luisamartins.medreminder.repository.useregister

import dev.luisamartins.medreminder.data.useregister.UseRegisterEntity
import dev.luisamartins.medreminder.model.UseRegister

fun UseRegisterEntity.toModel(): UseRegister {
    return UseRegister(
        id = id,
        medicationId = medicationId,
        date = date,
        timeOfUse = timeOfUse
    )
}

fun UseRegister.toEntity(): UseRegisterEntity {
    return UseRegisterEntity(
        id = id,
        medicationId = medicationId,
        date = date,
        timeOfUse = timeOfUse
    )
}