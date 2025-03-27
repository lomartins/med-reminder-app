package dev.luisamartins.medreminder.ui.di

import dev.luisamartins.medreminder.ui.screens.addmedication.AddMedicationViewModel
import dev.luisamartins.medreminder.ui.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::AddMedicationViewModel)
}