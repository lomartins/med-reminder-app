package dev.luisamartins.medreminder.model

data class UseRegister(
    val id: Long = 0,
    val medicationId: Long,
    val date: String,
    val timeOfUse: String
)
