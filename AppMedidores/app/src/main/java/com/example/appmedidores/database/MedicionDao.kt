package com.example.appmedidores.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appmedidores.data.Medicion
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicion: Medicion)

    @Query("SELECT * FROM mediciones ORDER BY fecha DESC")
    fun getAllMediciones(): Flow<List<Medicion>>
}