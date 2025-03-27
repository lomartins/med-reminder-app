package dev.luisamartins.medreminder.data.database.converters

import androidx.room.TypeConverter
import dev.luisamartins.medreminder.domain.model.DayOfTheWeek

class DayOfTheWeekConverters {
    @TypeConverter
    fun listFromString(value: String): List<DayOfTheWeek> {
        return value.split(',').map { DayOfTheWeek.valueOf(it) }
    }

    @TypeConverter
    fun fromList(value: List<DayOfTheWeek>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun fromString(value: String): DayOfTheWeek {
        return DayOfTheWeek.valueOf(value)
    }

    @TypeConverter
    fun toString(value: DayOfTheWeek): String {
        return value.name
    }
}