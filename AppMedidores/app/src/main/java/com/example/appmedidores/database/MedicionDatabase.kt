package com.example.appmedidores.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appmedidores.data.Medicion

@Database(entities = [Medicion::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MedicionDatabase : RoomDatabase() {
    abstract fun medicionDao(): MedicionDao
}