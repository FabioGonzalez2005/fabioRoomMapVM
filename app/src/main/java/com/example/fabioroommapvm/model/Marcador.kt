package com.example.fabioroommapvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Marcadores")
data class Marcador(
    // Asignamos la primary key
    @PrimaryKey(autoGenerate = true) val idMarcador: Int = 0,
    // Asignamos los otros elementos de la tabla
    @ColumnInfo(name = "Título") val tituloMarcador: String,
    @ColumnInfo(name = "CoordenadaX") val coordenadaX: Double,
    @ColumnInfo(name = "CoordenadaY") val coordenadaY: Double,
    val idTipoMarcador: Int
)
