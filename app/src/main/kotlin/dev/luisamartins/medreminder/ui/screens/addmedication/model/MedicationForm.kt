package dev.luisamartins.medreminder.ui.screens.addmedication.model

import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.MedicationType

data class MedicationForm(
    val title: String,
    val dosage: String,
    val dosageUnit: DosageUnit,
    val type: MedicationType,
    val time: String,
    val days: List<Int>,
    val startDate: String,
    val endDate: String
)
