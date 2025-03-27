package dev.luisamartins.medreminder.data.database.tables.useregister

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns

@Dao
interface UseRegisterDao {
    @Query("SELECT * FROM UseRegisterEntity")
    suspend fun getAll(): List<UseRegisterEntity>

    @Query("SELECT * FROM UseRegisterEntity WHERE date = :date")
    suspend fun getForDate(date: String): List<UseRegisterEntity>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM UseRegisterEntity JOIN ScheduleEntity ON UseRegisterEntity.schedule_id = ScheduleEntity.id WHERE ScheduleEntity.medication_id = :medicationId")
    suspend fun getForMedication(medicationId: Long): List<UseRegisterEntity>

    @Insert
    suspend fun insertAll(vararg useRegisters: UseRegisterEntity)
}