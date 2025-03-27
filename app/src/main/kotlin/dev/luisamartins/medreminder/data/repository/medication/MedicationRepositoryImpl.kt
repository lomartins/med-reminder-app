package dev.luisamartins.medreminder.data.repository.medication

import dev.luisamartins.medreminder.data.datasource.MedicationDataSource
import dev.luisamartins.medreminder.domain.model.Medication
import dev.luisamartins.medreminder.domain.repository.MedicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicationRepositoryImpl(
    private val medicationDataSource: MedicationDataSource
) : MedicationRepository {
    override suspend fun getAll(): Result<List<Medication>> = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.success(medicationDataSource.getAll().map { it.toModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getById(id: Long): Result<Medication> = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.success(medicationDataSource.getById(id).toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertAll(vararg medications: Medication): Result<Unit> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                medicationDataSource.insertAll(*medications.map { it.toEntity() }.toTypedArray())
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}