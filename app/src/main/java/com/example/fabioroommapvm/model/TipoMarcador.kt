package com.example.fabioroommapvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TiposMarcadores")
data class TipoMarcador(
    // Asignamos la primary key
    @PrimaryKey(autoGenerate = true) val idTipoMarcador: Int = 0,
    val tituloTipoMarcador: String
)