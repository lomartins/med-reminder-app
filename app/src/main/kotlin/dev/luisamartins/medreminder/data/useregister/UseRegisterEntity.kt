package dev.luisamartins.medreminder.data.useregister

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import dev.luisamartins.medreminder.data.medication.MedicationEntity

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
data class UseRegisterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "medication_id")
    val medicationId: Long,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time_of_use")
    val timeOfUse: String,
)
