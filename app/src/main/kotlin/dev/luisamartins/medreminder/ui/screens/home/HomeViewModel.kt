package dev.luisamartins.medreminder.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.model.UseRegister
import dev.luisamartins.medreminder.repository.medication.MedicationRepository
import dev.luisamartins.medreminder.repository.useregister.UseRegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val medicationRepository: MedicationRepository,
    private val useRegisterRepository: UseRegisterRepository
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    fun loadMedications() {
        Log.d(TAG, "loadMedications()")
        viewModelScope.launch(Dispatchers.IO) {

            Log.d(TAG, "loadMedications: viewModelScope.launch")
            state = state.copy(isLoading = true)
            val date = LocalDate.Formats.ISO.format(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
            val usages = useRegisterRepository.getForDate(date)
            state = state.copy(medications = medicationRepository.getAll().map {
                it.copy(
                    checked = usages.any { usage -> usage.medicationId == it.id }
                )
            })
            state = state.copy(isLoading = false)
            Log.d(TAG, "loadMedications: state = $state")
        }
        Log.d(TAG, "loadMedications")
    }
    
    fun markMedicationAsChecked(medication: Medication) {
        viewModelScope.launch { 
            val dateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val date = LocalDate.Formats.ISO.format(dateTime.date)
            val timeOfUse = LocalDateTime.Formats.ISO.format(dateTime)
            useRegisterRepository.insert(
                UseRegister(
                    medicationId = medication.id,
                    date = date,
                    timeOfUse = timeOfUse
                )
            )
            loadMedications()
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}