package com.example.fabioroommapvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Database(entities = [Marcador::class, TipoMarcador::class], version = 8)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marcadorDao(): MarcadorDao
    abstract fun tipoMarcadorDao(): TipoMarcadorDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        @OptIn(DelicateCoroutinesApi::class)
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "marcadores_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                GlobalScope.launch {
                    datosIniciales(instance.tipoMarcadorDao(), instance.marcadorDao())
                }
                instance
            }
        }

        private suspend fun datosIniciales(tipoMarcadorDao: TipoMarcadorDao, marcadorDao: MarcadorDao) {
            val tipos = listOf(
                TipoMarcador(tituloTipoMarcador = "Restaurante"),
                TipoMarcador(tituloTipoMarcador = "Supermercado"),
                TipoMarcador(tituloTipoMarcador = "Playa"),
                TipoMarcador(tituloTipoMarcador = "Biblioteca")
            )
            val tiposFromDb = tipoMarcadorDao.obtenerTodosTipos().first()

            if (tiposFromDb.isEmpty()) {
                tipos.forEach {
                    tipoMarcadorDao.insertarTipoMarcador(it)
                }
            }

            val tiposFromDbUpdated = tipoMarcadorDao.obtenerTodosTipos().first()
            val marcadores = listOf(
                Marcador(tituloMarcador = "Restaurante La Tegala", coordenadaX = 28.972511, coordenadaY = -13.661507, idTipoMarcadorOwner = tiposFromDbUpdated[0].idTipoMarcador),
                Marcador(tituloMarcador = "El Barquillo", coordenadaX = 29.029535, coordenadaY = -13.519138, idTipoMarcadorOwner = tiposFromDbUpdated[0].idTipoMarcador),
                Marcador(tituloMarcador = "Supermercado Spar", coordenadaX = 29.003576, coordenadaY = -13.534444, idTipoMarcadorOwner = tiposFromDbUpdated[1].idTipoMarcador),
                Marcador(tituloMarcador = "Superdino", coordenadaX = 28.992539, coordenadaY = -13.544739, idTipoMarcadorOwner = tiposFromDbUpdated[1].idTipoMarcador),
                Marcador(tituloMarcador = "Playa de Papagayo", coordenadaX = 28.931591, coordenadaY = -13.832378, idTipoMarcadorOwner = tiposFromDbUpdated[2].idTipoMarcador),
                Marcador(tituloMarcador = "Playa Blanca", coordenadaX = 28.869973, coordenadaY = -13.861702, idTipoMarcadorOwner = tiposFromDbUpdated[2].idTipoMarcador),
                Marcador(tituloMarcador = "Playa de Famara", coordenadaX = 29.039871, coordenadaY = -13.527636, idTipoMarcadorOwner = tiposFromDbUpdated[2].idTipoMarcador),
                Marcador(tituloMarcador = "Biblioteca Municipal de Arrecife", coordenadaX = 28.963180, coordenadaY = -13.550132, idTipoMarcadorOwner = tiposFromDbUpdated[3].idTipoMarcador),
                Marcador(tituloMarcador = "Biblioteca de San Bartolomé", coordenadaX = 28.961247, coordenadaY = -13.571539, idTipoMarcadorOwner = tiposFromDbUpdated[3].idTipoMarcador),
                Marcador(tituloMarcador = "Biblioteca de Teguise", coordenadaX = 29.041682, coordenadaY = -13.513833, idTipoMarcadorOwner = tiposFromDbUpdated[3].idTipoMarcador)
            )

            marcadores.forEach {
                marcadorDao.insertarMarcador(it)
            }
        }
    }
}
