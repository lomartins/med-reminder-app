package dev.luisamartins.medreminder.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.luisamartins.medreminder.data.medication.MedicationDao
import dev.luisamartins.medreminder.data.medication.MedicationEntity
import dev.luisamartins.medreminder.data.useregister.UseRegisterDao
import dev.luisamartins.medreminder.data.useregister.UseRegisterEntity

@Database(entities = [MedicationEntity::class, UseRegisterEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun medicationDao(): MedicationDao
    abstract fun useRegisterDao(): UseRegisterDao
}