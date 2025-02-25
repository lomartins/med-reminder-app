package dev.luisamartins.medreminder.di

import androidx.room.Room
import dev.luisamartins.medreminder.data.AppDatabase
import dev.luisamartins.medreminder.repository.medication.MedicationRepository
import dev.luisamartins.medreminder.repository.medication.MedicationRepositoryImpl
import dev.luisamartins.medreminder.repository.useregister.UseRegisterRepository
import dev.luisamartins.medreminder.repository.useregister.UseRegisterRepositoryImpl
import dev.luisamartins.medreminder.ui.screens.home.HomeViewModel
import dev.luisamartins.medreminder.ui.screens.addmedication.AddMedicationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::AddMedicationViewModel)

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "medreminder-db"
        ).build()
    }

    factory { get<AppDatabase>().medicationDao() }
    factory { get<AppDatabase>().useRegisterDao() }
    factoryOf(::MedicationRepositoryImpl) bind MedicationRepository::class
    factoryOf(::UseRegisterRepositoryImpl) bind UseRegisterRepository::class
}