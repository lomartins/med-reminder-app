package dev.luisamartins.medreminder.data.repository.medication

import dev.luisamartins.medreminder.data.database.tables.medication.MedicationEntity
import dev.luisamartins.medreminder.data.model.MedicationTypeData
import dev.luisamartins.medreminder.domain.model.Medication
import dev.luisamartins.medreminder.domain.model.MedicationType


fun MedicationEntity.toModel(): Medication {
    return Medication(
        id = id,
        name = name,
        type = when (type) {
            MedicationTypeData.PILL -> MedicationType.PILL
            MedicationTypeData.TABLET -> MedicationType.TABLET
            MedicationTypeData.INJECTION -> MedicationType.INJECTION
            MedicationTypeData.SYRUP -> MedicationType.SYRUP
            MedicationTypeData.DROPS -> MedicationType.DROPS
        }
    )
}

fun Medication.toEntity(): MedicationEntity {
    return MedicationEntity(
        id = id,
        name = name,
        type = when (type) {
            MedicationType.PILL -> MedicationTypeData.PILL
            MedicationType.TABLET -> MedicationTypeData.TABLET
            MedicationType.INJECTION -> MedicationTypeData.INJECTION
            MedicationType.SYRUP -> MedicationTypeData.SYRUP
            MedicationType.DROPS -> MedicationTypeData.DROPS
        }
    )
}