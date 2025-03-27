package dev.luisamartins.medreminder.data.database.tables.schedule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.luisamartins.medreminder.domain.model.DayOfTheWeek

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM ScheduleEntity")
    suspend fun getAll(): List<ScheduleEntity>

    @Query("SELECT * FROM ScheduleEntity WHERE medication_id = :medicationId")
    suspend fun getForMedication(medicationId: Long): List<ScheduleEntity>

    @Query("SELECT * FROM ScheduleEntity WHERE days_of_the_week LIKE '%' || :dayOfTheWeek || '%'")
    suspend fun getForDayOfTheWeek(dayOfTheWeek: DayOfTheWeek): List<ScheduleEntity>

    @Insert
    suspend fun insertAll(vararg schedules: ScheduleEntity)
}