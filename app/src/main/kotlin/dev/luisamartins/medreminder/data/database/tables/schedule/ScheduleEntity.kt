package dev.luisamartins.medreminder.data.database.tables.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import dev.luisamartins.medreminder.data.database.tables.medication.MedicationEntity
import dev.luisamartins.medreminder.domain.model.DayOfTheWeek
import kotlinx.datetime.LocalTime

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = MedicationEntity::class,
            parentColumns = ["id"],
            childColumns = ["medication_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "medication_id")
    val medicationId: Long,
    @ColumnInfo(name = "time")
    val time: LocalTime,
    @ColumnInfo(name = "days_of_the_week")
    val daysOfTheWeek: List<DayOfTheWeek>,
)
