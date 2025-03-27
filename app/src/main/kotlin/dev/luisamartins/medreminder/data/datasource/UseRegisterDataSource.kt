package dev.luisamartins.medreminder.data.datasource

import dev.luisamartins.medreminder.data.database.tables.useregister.UseRegisterEntity

interface UseRegisterDataSource {
    suspend fun getAll(): List<UseRegisterEntity>
    suspend fun getForDate(date: String): List<UseRegisterEntity>
    suspend fun getForMedication(medicationId: Long): List<UseRegisterEntity>
    suspend fun insertAll(vararg useRegisters: UseRegisterEntity)
}