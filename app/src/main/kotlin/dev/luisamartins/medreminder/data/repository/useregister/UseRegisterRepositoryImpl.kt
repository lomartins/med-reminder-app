package dev.luisamartins.medreminder.data.repository.useregister

import dev.luisamartins.medreminder.data.database.tables.useregister.UseRegisterDao
import dev.luisamartins.medreminder.domain.model.UseRegister
import dev.luisamartins.medreminder.domain.repository.UseRegisterRepository

class UseRegisterRepositoryImpl(
    private val useRegisterDao: UseRegisterDao
) : UseRegisterRepository {
    override suspend fun getAll(): List<UseRegister> {
        return useRegisterDao.getAll().map { it.toModel() }
    }

    override suspend fun getForDate(date: String): List<UseRegister> {
        return useRegisterDao.getForDate(date).map { it.toModel() }
    }

    override suspend fun getForMedication(medicationId: Long): List<UseRegister> {
        return useRegisterDao.getForMedication(medicationId).map { it.toModel() }
    }

    override suspend fun insertAll(vararg useRegisters: UseRegister) {
        useRegisterDao.insertAll(*(useRegisters.map { it.toEntity() }).toTypedArray())
    }
}