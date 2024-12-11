package com.example.fabioroommapvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MarcadorDao {

    @Insert
    // Función para insertar marcador
    suspend fun insertarMarcador(marcador: Marcador)

    @Query("SELECT * FROM MARCADORES")
    // Flow proporciona una lista de todos los marcadores con su tipo
    fun obtenerTodosMarcadoresYTipos(): Flow<List<MarcadorConTipo>>
}
