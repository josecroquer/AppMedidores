package com.example.appmedidores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.appmedidores.database.MedicionDatabase
import com.example.appmedidores.ui.MedicionViewModel
import com.example.appmedidores.ui.MedicionViewModelFactory
import com.example.appmedidores.ui.screens.ListaMedicionesScreen
import com.example.appmedidores.ui.screens.RegistroMedicionScreen
import com.example.appmedidores.ui.theme.AppMedidoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            MedicionDatabase::class.java,
            "mediciones_db"
        ).build()

        setContent {
            AppMedidoresTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val viewModel: MedicionViewModel = viewModel(
                        factory = MedicionViewModelFactory(db.medicionDao())
                    )

                    NavHost(navController = navController, startDestination = "lista") {
                        composable("lista") {
                            ListaMedicionesScreen(
                                viewModel = viewModel,
                                onNavigateToRegistro = { navController.navigate("registro") }
                            )
                        }
                        composable("registro") {
                            RegistroMedicionScreen(
                                viewModel = viewModel,
                                onGuardarSuccess = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}