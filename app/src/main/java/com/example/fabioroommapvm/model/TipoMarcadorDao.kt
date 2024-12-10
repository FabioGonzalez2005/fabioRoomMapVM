package com.example.fabioroommapvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoMarcadorDao {

    @Insert
    suspend fun insertarTipoMarcador(tipoMarcador: TipoMarcador)

    @Query("SELECT * FROM TiposMarcadores")
    fun obtenerTodosTipos(): Flow<List<TipoMarcador>>
}
