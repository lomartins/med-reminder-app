package dev.luisamartins.medreminder.model

data class Medication(
    val id: Int,
    val name: String,
    val dosage: Int,
    val time: String,
    val dosageUnit: DosageUnit,
    val remainingTimeInDays: Int,
    val type: MedicationType
)
