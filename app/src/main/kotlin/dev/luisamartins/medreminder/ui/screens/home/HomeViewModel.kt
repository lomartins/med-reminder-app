package dev.luisamartins.medreminder.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    @OptIn(SavedStateHandleSaveableApi::class)
    var state: HomeUiState by savedStateHandle.saveable {
        mutableStateOf(HomeUiState())
    }
        private set

    fun loadMedications() {
        Log.d(TAG, "loadMedications()")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "loadMedications: viewModelScope.launch")
            state = state.copy(isLoading = true)

            val date = LocalDate.Formats.ISO.format(
                Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            )

            state = state.copy(
                medications = emptyList(), // TODO
                isLoading = false
            )
            Log.d(TAG, "loadMedications: state = $state")
        }
        Log.d(TAG, "loadMedications")
    }

    fun checkMedication(medication: HomeMedicationItemUiState) {
        viewModelScope.launch {
            val dateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val date = LocalDate.Formats.ISO.format(dateTime.date)
            TODO()
            loadMedications()
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
        private const val STATE = "state"
    }
}
