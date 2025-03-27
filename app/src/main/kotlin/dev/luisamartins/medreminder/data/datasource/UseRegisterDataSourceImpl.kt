package dev.luisamartins.medreminder.data.datasource

import dev.luisamartins.medreminder.data.database.tables.useregister.UseRegisterDao
import dev.luisamartins.medreminder.data.database.tables.useregister.UseRegisterEntity

class UseRegisterDataSourceImpl(
    private val useRegisterDao: UseRegisterDao
) : UseRegisterDataSource {
    override suspend fun getAll(): List<UseRegisterEntity> {
        return useRegisterDao.getAll()
    }

    override suspend fun getForDate(date: String): List<UseRegisterEntity> {
        return useRegisterDao.getForDate(date)
    }

    override suspend fun getForMedication(medicationId: Long): List<UseRegisterEntity> {
        return useRegisterDao.getForMedication(medicationId)
    }

    override suspend fun insertAll(vararg useRegisters: UseRegisterEntity) {
        return useRegisterDao.insertAll(*useRegisters)
    }
}