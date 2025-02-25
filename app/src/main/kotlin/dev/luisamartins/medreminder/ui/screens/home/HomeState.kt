package dev.luisamartins.medreminder.ui.screens.home

import dev.luisamartins.medreminder.model.Medication

data class HomeState(
    val medications: List<Medication> = emptyList(),
    val isLoading: Boolean = false
)
