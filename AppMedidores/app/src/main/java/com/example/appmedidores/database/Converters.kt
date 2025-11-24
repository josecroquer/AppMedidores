package com.example.appmedidores.database

import androidx.room.TypeConverter
import com.example.appmedidores.data.TipoGasto

class Converters {
    @TypeConverter
    fun fromTipoGasto(tipo: TipoGasto): String = tipo.name

    @TypeConverter
    fun toTipoGasto(tipoString: String): TipoGasto = TipoGasto.valueOf(tipoString)
}