package com.example.appmedidores.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmedidores.data.Medicion
import com.example.appmedidores.data.TipoGasto
import com.example.appmedidores.database.MedicionDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class RegistroState(
    val valor: String = "",
    val tipoSeleccionado: TipoGasto = TipoGasto.AGUA,
    val fecha: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
)

class MedicionViewModel(private val medicionDao: MedicionDao) : ViewModel() {

    val medicionesUiState: StateFlow<List<Medicion>> =
        medicionDao.getAllMediciones()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    private val _registroState = MutableStateFlow(RegistroState())
    val registroState: StateFlow<RegistroState> = _registroState

    fun updateValor(newValor: String) {
        _registroState.value = _registroState.value.copy(valor = newValor)
    }

    fun updateTipo(newTipo: TipoGasto) {
        _registroState.value = _registroState.value.copy(tipoSeleccionado = newTipo)
    }

    fun guardarMedicion() {
        val valorDouble = _registroState.value.valor.toDoubleOrNull()
        if (valorDouble != null && valorDouble > 0) {
            val nuevaMedicion = Medicion(
                tipoGasto = _registroState.value.tipoSeleccionado,
                valor = valorDouble,
                fecha = _registroState.value.fecha
            )
            viewModelScope.launch {
                medicionDao.insert(nuevaMedicion)
                _registroState.value = _registroState.value.copy(valor = "")
            }
        }
    }
}