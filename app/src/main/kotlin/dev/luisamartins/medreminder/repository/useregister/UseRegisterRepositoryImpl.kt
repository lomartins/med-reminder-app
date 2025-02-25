package dev.luisamartins.medreminder.repository.useregister

import dev.luisamartins.medreminder.data.useregister.UseRegisterDao
import dev.luisamartins.medreminder.model.UseRegister

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

    override suspend fun insert(useRegister: UseRegister) {
        useRegisterDao.insert(useRegister.toEntity())
    }
}