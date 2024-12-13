package com.example.fabioroommapvm.viewModel

import androidx.lifecycle.ViewModel
import com.example.fabioroommapvm.model.MarcadorConTipo
import com.example.fabioroommapvm.model.MarcadorDao
import kotlinx.coroutines.flow.Flow

// Clase ViewModel que conecta la capa de datos con la interfaz de usuario.
class MarcadorVistaModelo(private val marcadorDao: MarcadorDao) : ViewModel() {
    // Flujo que proporciona una lista de marcadores con su tipo asociado desde la base de datos.
    val marcadoresConTipo: Flow<List<MarcadorConTipo>> = marcadorDao.obtenerTodosMarcadoresYTipos()
}

