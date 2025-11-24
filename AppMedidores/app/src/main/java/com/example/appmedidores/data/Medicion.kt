package com.example.appmedidores.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mediciones")
data class Medicion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipoGasto: TipoGasto,
    val valor: Double,
    val fecha: String
)