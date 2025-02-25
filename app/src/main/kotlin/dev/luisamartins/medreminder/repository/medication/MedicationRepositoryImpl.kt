package dev.luisamartins.medreminder.repository.medication

import dev.luisamartins.medreminder.data.medication.MedicationDao
import dev.luisamartins.medreminder.model.Medication

class MedicationRepositoryImpl(
    private val medicationDao: MedicationDao
) : MedicationRepository {
    override suspend fun getAll(): List<Medication> {
        return medicationDao.getAll().map { it.toModel() }
    }

    override suspend fun getById(id: Long): Medication {
        return medicationDao.getById(id).toModel()
    }

    override suspend fun insertAll(vararg medications: Medication) {
        medicationDao.insertAll(*medications.map { it.toEntity() }.toTypedArray())
    }
}