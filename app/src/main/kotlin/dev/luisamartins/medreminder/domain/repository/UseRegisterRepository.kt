package dev.luisamartins.medreminder.domain.repository

import dev.luisamartins.medreminder.domain.model.UseRegister

interface UseRegisterRepository {
    suspend fun getAll(): List<UseRegister>
    suspend fun getForDate(date: String): List<UseRegister>
    suspend fun getForMedication(medicationId: Long): List<UseRegister>
    suspend fun insertAll(vararg useRegisters: UseRegister)
}