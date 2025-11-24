package com.example.appmedidores.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.appmedidores.R
import com.example.appmedidores.data.TipoGasto
import com.example.appmedidores.ui.MedicionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroMedicionScreen(
    viewModel: MedicionViewModel,
    onGuardarSuccess: () -> Unit
) {
    val state by viewModel.registroState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.registro_titulo)) }) }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.valor,
                onValueChange = { viewModel.updateValor(it) },
                label = { Text(stringResource(R.string.registro_label_valor)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = state.fecha,
                onValueChange = {},
                label = { Text(stringResource(R.string.registro_label_fecha)) },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))
            Text(stringResource(R.string.registro_label_tipo), style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))

            Column(Modifier.selectableGroup()) {
                listOf(TipoGasto.AGUA, TipoGasto.LUZ, TipoGasto.GAS).forEach { tipo ->
                    Row(
                        Modifier.fillMaxWidth().height(56.dp)
                            .selectable(selected = (tipo == state.tipoSeleccionado), onClick = { viewModel.updateTipo(tipo) }, role = Role.RadioButton)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = (tipo == state.tipoSeleccionado), onClick = null)
                        Spacer(Modifier.width(16.dp))
                        Text(text = when(tipo) {
                            TipoGasto.AGUA -> stringResource(R.string.registro_opcion_agua)
                            TipoGasto.LUZ -> stringResource(R.string.registro_opcion_luz)
                            TipoGasto.GAS -> stringResource(R.string.registro_opcion_gas)
                        })
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
            Button(
                onClick = {
                    viewModel.guardarMedicion()
                    onGuardarSuccess()
                },
                enabled = state.valor.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.registro_btn_guardar))
            }
        }
    }
}