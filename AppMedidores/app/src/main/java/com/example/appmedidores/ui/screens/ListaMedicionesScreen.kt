package com.example.appmedidores.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.GasMeter
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.appmedidores.R
import com.example.appmedidores.data.Medicion
import com.example.appmedidores.data.TipoGasto
import com.example.appmedidores.ui.MedicionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaMedicionesScreen(
    viewModel: MedicionViewModel,
    onNavigateToRegistro: () -> Unit
) {
    val mediciones by viewModel.medicionesUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.lista_titulo)) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToRegistro) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        if (mediciones.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.lista_registro_vacio))
            }
        } else {
            LazyColumn(contentPadding = padding) {
                items(mediciones) { medicion ->
                    MedicionItem(medicion)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun MedicionItem(medicion: Medicion) {
    val (icon, color) = when (medicion.tipoGasto) {
        TipoGasto.AGUA -> Icons.Filled.WaterDrop to MaterialTheme.colorScheme.primary
        TipoGasto.LUZ -> Icons.Filled.ElectricBolt to MaterialTheme.colorScheme.tertiary
        TipoGasto.GAS -> Icons.Filled.GasMeter to MaterialTheme.colorScheme.secondary
    }

    Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(32.dp))
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = when(medicion.tipoGasto) {
                    TipoGasto.AGUA -> stringResource(R.string.tipo_agua)
                    TipoGasto.LUZ -> stringResource(R.string.tipo_luz)
                    TipoGasto.GAS -> stringResource(R.string.tipo_gas)
                },
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = medicion.fecha, style = MaterialTheme.typography.bodySmall)
        }
        Text(text = "${medicion.valor}", style = MaterialTheme.typography.headlineSmall)
    }
}