package dev.luisamartins.medreminder.data.datasource

import dev.luisamartins.medreminder.data.database.tables.medication.MedicationEntity

interface MedicationDataSource {
    suspend fun getAll(): List<MedicationEntity>
    suspend fun getById(id: Long): MedicationEntity
    suspend fun insertAll(vararg medications: MedicationEntity)
}