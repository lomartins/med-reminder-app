package dev.luisamartins.medreminder.domain.repository

import dev.luisamartins.medreminder.domain.model.Medication

interface MedicationRepository {
    suspend fun getAll(): Result<List<Medication>>
    suspend fun getById(id: Long): Result<Medication>
    suspend fun insertAll(vararg medications: Medication): Result<Unit>
}