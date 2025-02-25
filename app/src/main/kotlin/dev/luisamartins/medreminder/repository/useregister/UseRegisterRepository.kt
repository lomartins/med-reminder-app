package dev.luisamartins.medreminder.repository.useregister

import dev.luisamartins.medreminder.model.UseRegister
import kotlinx.datetime.LocalDateTime

interface UseRegisterRepository {
    suspend fun getAll(): List<UseRegister>
    suspend fun getForDate(date: String): List<UseRegister>
    suspend fun getForMedication(medicationId: Long): List<UseRegister>
    suspend fun insert(useRegister: UseRegister)
}