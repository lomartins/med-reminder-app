package dev.luisamartins.medreminder.ui.screens.addmedication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.repository.medication.MedicationRepository
import dev.luisamartins.medreminder.ui.screens.addmedication.model.MedicationForm
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatterBuilder

class AddMedicationViewModel(
    private val medicationRepository: MedicationRepository
) : ViewModel() {
    fun addMedication(medicationForm: MedicationForm) {
        viewModelScope.launch {
            val medications = medicationForm.times.map {
                Medication(
                    name = medicationForm.title,
                    dosage = medicationForm.dosage.toIntOrNull()
                        ?: throw IllegalArgumentException("Invalid dosage"),
                    dosageUnit = medicationForm.dosageUnit,
                    type = medicationForm.type,
                    time = it,
                    startDate = LocalDate.Formats.ISO.format(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
                )
            }
            medicationRepository.insertAll(*medications.toTypedArray())
        }
    }
}