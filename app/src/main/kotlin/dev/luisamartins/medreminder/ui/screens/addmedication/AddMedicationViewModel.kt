package dev.luisamartins.medreminder.ui.screens.addmedication

import androidx.lifecycle.ViewModel
import dev.luisamartins.medreminder.ui.screens.addmedication.model.MedicationForm

class AddMedicationViewModel(
//    private val medicationRepository: MedicationRepository
) : ViewModel() {
    fun addMedication(medicationForm: MedicationForm) {
//        viewModelScope.launch {
//            val medications = medicationForm.times.map {
//                Medication(
//                    name = medicationForm.title,
//                    dosage = medicationForm.dosage.toIntOrNull()
//                        ?: throw IllegalArgumentException("Invalid dosage"),
//                    dosageUnit = medicationForm.dosageUnit,
//                    type = medicationForm.type,
//                    time = it,
//                    startDate = LocalDate.Formats.ISO.format(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
//                )
//            }
//            medicationRepository.insertAll(*medications.toTypedArray())
//        }
    }
}