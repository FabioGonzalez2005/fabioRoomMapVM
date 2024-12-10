package com.example.fabioroommapvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow

class MarcadorVistaModelo(private val marcadorDao: MarcadorDao) : ViewModel() {
    val marcadoresConTipo: Flow<List<MarcadorConTipo>> = marcadorDao.obtenerTodosMarcadoresYTipos()
}

class ViewModelFactory(private val marcadorDao: MarcadorDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarcadorVistaModelo::class.java)) {
            return MarcadorVistaModelo(marcadorDao) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}
