package dev.luisamartins.medreminder.data.database.tables.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.luisamartins.medreminder.data.model.MedicationTypeData

@Entity
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: MedicationTypeData,
)