package com.example.fabioroommapvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fabioroommapvm.model.MarcadorDao
import com.example.fabioroommapvm.model.MarcadorConTipo
import kotlinx.coroutines.flow.Flow

// Clase ViewModel que conecta la capa de datos con la interfaz de usuario.
class MarcadorVistaModelo(private val marcadorDao: MarcadorDao) : ViewModel() {
    // Flujo que proporciona una lista de marcadores con su tipo asociado desde la base de datos.
    val marcadoresConTipo: Flow<List<MarcadorConTipo>> = marcadorDao.obtenerTodosMarcadoresYTipos()
}

// Fábrica para crear instancias de ViewModel con parámetros personalizados.
class ViewModelFactory(private val marcadorDao: MarcadorDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Comprueba si el ViewModel solicitado es MarcadorVistaModelo y lo crea.
        if (modelClass.isAssignableFrom(MarcadorVistaModelo::class.java)) {
            return MarcadorVistaModelo(marcadorDao) as T
        }
        // Lanza una excepción si se solicita un ViewModel desconocido.
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}
