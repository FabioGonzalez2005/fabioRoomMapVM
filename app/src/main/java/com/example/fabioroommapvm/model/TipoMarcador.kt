package com.example.fabioroommapvm.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

// Clase que define los tipos de marcadores.
@Entity(tableName = "TiposMarcadores")
data class TipoMarcador(
    // Asignamos la primary key.
    @PrimaryKey(autoGenerate = true) val idTipoMarcador: Int = 0,
    val tituloTipoMarcador: String
)

// Clase de datos que representa la relación entre un marcador y sus tipos asociados.
data class MarcadorConTipo(
    // El marcador principal, almacenado como un objeto incrustado en esta relación.
    @Embedded val marcador: Marcador,

    @Relation(
        parentColumn = "idTipoMarcador",
        entityColumn = "idTipoMarcador"
    )
    val tiposMarcadores: List<TipoMarcador> // Lista de tipos relacionados con el marcador.
)
