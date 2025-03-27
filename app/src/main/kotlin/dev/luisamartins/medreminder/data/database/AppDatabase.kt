package dev.luisamartins.medreminder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.luisamartins.medreminder.data.database.converters.DayOfTheWeekConverters
import dev.luisamartins.medreminder.data.database.converters.LocalDateTimeConverters
import dev.luisamartins.medreminder.data.database.converters.LocalTimeConverters
import dev.luisamartins.medreminder.data.database.converters.MedicationTypeConverters
import dev.luisamartins.medreminder.data.database.tables.medication.MedicationDao
import dev.luisamartins.medreminder.data.database.tables.medication.MedicationEntity
import dev.luisamartins.medreminder.data.database.tables.schedule.ScheduleEntity
import dev.luisamartins.medreminder.data.database.tables.useregister.UseRegisterDao
import dev.luisamartins.medreminder.data.database.tables.useregister.UseRegisterEntity

@Database(
    entities = [
        MedicationEntity::class,
        UseRegisterEntity::class,
        ScheduleEntity::class
    ],
    version = 1
)
@TypeConverters(
    LocalTimeConverters::class,
    LocalDateTimeConverters::class,
    DayOfTheWeekConverters::class,
    MedicationTypeConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicationDao(): MedicationDao
    abstract fun useRegisterDao(): UseRegisterDao
}