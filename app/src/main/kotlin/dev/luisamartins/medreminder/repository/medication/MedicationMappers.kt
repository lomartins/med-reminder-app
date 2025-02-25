package dev.luisamartins.medreminder.repository.medication

import dev.luisamartins.medreminder.data.medication.MedicationEntity
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.model.MedicationType


fun MedicationEntity.toModel(): Medication {
    return Medication(
        id = id,
        name = name,
        dosage = dosage,
        time = time,
        dosageUnit = DosageUnit.valueOf(dosageUnit),
        type = MedicationType.valueOf(type),
        startDate = startDate
    )
}

fun Medication.toEntity(): MedicationEntity {
    return MedicationEntity(
        id = id,
        name = name,
        dosage = dosage,
        time = time,
        dosageUnit = dosageUnit.name,
        type = type.name,
        startDate = startDate
    )
}