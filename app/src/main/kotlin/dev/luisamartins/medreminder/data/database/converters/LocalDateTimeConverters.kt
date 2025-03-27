package dev.luisamartins.medreminder.data.database.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class LocalDateTimeConverters {
    @TypeConverter
    fun localDateTimeFromLong(value: Long): LocalDateTime {
        return Instant.fromEpochSeconds(value).toLocalDateTime(TimeZone.currentSystemDefault())
    }

    @TypeConverter
    fun localDateTimeToLong(value: LocalDateTime): Long {
        return value.toInstant(TimeZone.currentSystemDefault()).epochSeconds
    }
}