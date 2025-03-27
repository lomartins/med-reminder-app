package dev.luisamartins.medreminder.data.database.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalTime

class LocalTimeConverters {
    @TypeConverter
    fun localTimeFromInt(value: Int): LocalTime {
        return LocalTime.fromSecondOfDay(value)
    }

    @TypeConverter
    fun localTimeToInt(value: LocalTime): Int {
        return value.toSecondOfDay()
    }
}