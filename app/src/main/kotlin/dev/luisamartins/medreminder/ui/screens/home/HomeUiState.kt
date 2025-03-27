package dev.luisamartins.medreminder.ui.screens.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class HomeUiState(
    val medications: List<HomeMedicationItemUiState> = emptyList(),
    val isLoading: Boolean = false
) : Parcelable

@Parcelize
data class HomeMedicationItemUiState(
    val identifier: Long = -1,
    val name: String,
    val time: String,
    val type: MedicationTypeUiState,
    val typeDescription: String,
    val dosage: String,
    val remainingTime: String?,
    val isOverdue: Boolean,
    val isTaken: Boolean
) : Parcelable

@Parcelize
enum class MedicationTypeUiState : Parcelable {
    PILL,
    TABLET,
    INJECTION,
    SYRUP,
    DROPS
}