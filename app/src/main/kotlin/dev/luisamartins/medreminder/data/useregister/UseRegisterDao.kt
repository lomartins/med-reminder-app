package dev.luisamartins.medreminder.data.useregister

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UseRegisterDao {
    @Query("SELECT * FROM UseRegisterEntity")
    suspend fun getAll(): List<UseRegisterEntity>

    @Query("SELECT * FROM UseRegisterEntity WHERE date = :date")
    suspend fun getForDate(date: String): List<UseRegisterEntity>

    @Query("SELECT * FROM UseRegisterEntity WHERE medication_id = :medicationId")
    suspend fun getForMedication(medicationId: Long): List<UseRegisterEntity>

    @Insert
    suspend fun insert(useRegister: UseRegisterEntity)
}