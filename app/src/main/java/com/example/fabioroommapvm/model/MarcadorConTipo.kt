package com.example.fabioroommapvm.model

import androidx.room.Embedded
import androidx.room.Relation

// Clase de datos que representa la relación entre un marcador y sus tipos asociados.
data class MarcadorConTipo(
    // El marcador principal, almacenado como un objeto incrustado en esta relación.
    @Embedded val marcador: Marcador,

    // Relación entre el marcador y sus tipos.
    // parentColumn: Columna en la tabla de marcadores que actúa como clave foránea.
    // entityColumn: Columna en la tabla de tipos que coincide con la clave foránea.
    @Relation(
        parentColumn = "idTipoMarcadorOwner",
        entityColumn = "idTipoMarcador"
    )
    val tiposMarcadores: List<TipoMarcador> // Lista de tipos relacionados con el marcador.
)
