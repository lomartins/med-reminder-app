package dev.luisamartins.medreminder.data.database.tables.useregister

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import dev.luisamartins.medreminder.data.database.tables.schedule.ScheduleEntity
import kotlinx.datetime.LocalDateTime

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ScheduleEntity::class,
            parentColumns = ["id"],
            childColumns = ["schedule_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UseRegisterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "schedule_id")
    val scheduleId: Long,
    @ColumnInfo(name = "date")
    val date: LocalDateTime,
    @ColumnInfo(name = "usage_time_offset_in_minutes")
    val usageTimeOffsetInMinutes: Int
)
