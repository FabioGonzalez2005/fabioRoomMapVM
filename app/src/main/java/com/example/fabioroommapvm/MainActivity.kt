package com.example.fabioroommapvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fabioroommapvm.model.AppDatabase
import com.example.fabioroommapvm.ui.theme.ViewModelRMTheme
import com.example.fabioroommapvm.view.MapaVista
import com.example.fabioroommapvm.viewModel.MarcadorVistaModelo
import com.example.fabioroommapvm.viewModel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseDeDatos = AppDatabase.getDatabase(this)
        val daoMarcador = baseDeDatos.marcadorDao()

        enableEdgeToEdge()
        setContent {
            ViewModelRMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val vistaModelo: MarcadorVistaModelo = viewModel(factory = ViewModelFactory(daoMarcador))

                }
            }
        }
    }
}

