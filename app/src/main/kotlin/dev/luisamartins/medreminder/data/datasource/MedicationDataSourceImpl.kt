package dev.luisamartins.medreminder.data.datasource

import dev.luisamartins.medreminder.data.database.tables.medication.MedicationDao
import dev.luisamartins.medreminder.data.database.tables.medication.MedicationEntity

class MedicationDataSourceImpl(
    private val medicationDao: MedicationDao
) : MedicationDataSource {
    override suspend fun getAll(): List<MedicationEntity> {
        return medicationDao.getAll()
    }

    override suspend fun getById(id: Long): MedicationEntity {
        return medicationDao.getById(id)
    }

    override suspend fun insertAll(vararg medications: MedicationEntity) {
        return medicationDao.insertAll(*medications)
    }
}