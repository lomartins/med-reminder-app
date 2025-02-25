package dev.luisamartins.medreminder.data.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "dosage")
    val dosage: Int,
    @ColumnInfo(name = "dosage_unit")
    val dosageUnit: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "start_date")
    val startDate: String
)