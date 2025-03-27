package dev.luisamartins.medreminder.data.database.converters

import androidx.room.TypeConverter
import dev.luisamartins.medreminder.domain.model.MedicationType

class MedicationTypeConverters {
    @TypeConverter
    fun fromString(value: String): MedicationType {
        return MedicationType.valueOf(value)
    }

    @TypeConverter
    fun toString(value: MedicationType): String {
        return value.name
    }
}