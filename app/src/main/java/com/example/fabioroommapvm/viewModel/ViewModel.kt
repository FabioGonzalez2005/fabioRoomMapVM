package com.example.fabioroommapvm.viewModel

class MarcadorVistaModelo(private val marcadorDao: MarcadorDao) : ViewModel() {
    val marcadoresConTipo: Flow<List<MarcadorConTipo>> = marcadorDao.obtenerTodosMarcadoresYTipos()
}
}
