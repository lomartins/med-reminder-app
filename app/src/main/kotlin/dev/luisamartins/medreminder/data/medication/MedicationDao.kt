package dev.luisamartins.medreminder.data.medication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicationDao {
    @Query("SELECT * FROM MedicationEntity")
    suspend fun getAll(): List<MedicationEntity>
    @Query("SELECT * FROM MedicationEntity WHERE id = :id")
    suspend fun getById(id: Long): MedicationEntity

    @Insert
    suspend fun insertAll(vararg medications: MedicationEntity)
}