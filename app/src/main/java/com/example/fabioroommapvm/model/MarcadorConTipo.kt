package com.example.fabioroommapvm.model

import androidx.room.Embedded
import androidx.room.Relation

data class MarcadorConTipo(
    @Embedded val marcador: Marcador,
    @Relation(
        parentColumn = "idTipoMarcadorOwner",
        entityColumn = "idTipoMarcador"
    )
    val tiposMarcadores: List<TipoMarcador>
)