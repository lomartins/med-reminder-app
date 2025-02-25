package dev.luisamartins.medreminder.model

data class Medication(
    val id: Long = 0,
    val name: String,
    val dosage: Int,
    val time: String,
    val dosageUnit: DosageUnit,
    val type: MedicationType,
    val startDate: String = "",
    val checked: Boolean = false
)
