package dev.luisamartins.medreminder.ui.screens.addmedication.model

import dev.luisamartins.medreminder.domain.model.DosageUnit
import dev.luisamartins.medreminder.domain.model.MedicationType

data class MedicationForm(
    val title: String,
    val dosage: String,
    val dosageUnit: DosageUnit,
    val type: MedicationType,
    val times: List<String>,
)
