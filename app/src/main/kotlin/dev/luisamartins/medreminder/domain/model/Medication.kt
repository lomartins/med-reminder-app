package dev.luisamartins.medreminder.domain.model

data class Medication(
    val id: Long = 0,
    val name: String,
    val type: MedicationType,
)
