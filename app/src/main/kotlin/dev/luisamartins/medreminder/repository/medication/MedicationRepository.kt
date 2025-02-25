package dev.luisamartins.medreminder.repository.medication

import dev.luisamartins.medreminder.model.Medication

interface MedicationRepository {
    suspend fun getAll(): List<Medication>
    suspend fun getById(id: Long): Medication
    suspend fun insertAll(vararg medications: Medication)
}