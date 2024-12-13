package com.example.fabioroommapvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

// Clase que define la base de datos de la aplicación utilizando Room.
@Database(entities = [Marcador::class, TipoMarcador::class], version = 19)
abstract class AppDatabase : RoomDatabase() {

    abstract fun marcadorDao(): MarcadorDao
    abstract fun tipoMarcadorDao(): TipoMarcadorDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

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
                // Inicializa datos predeterminados en la base de datos en un hilo global.
                GlobalScope.launch {
                    try {
                        datosIniciales(instance.tipoMarcadorDao(), instance.marcadorDao())
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                instance
            }
        }

        // Función para insertar datos iniciales en la base de datos.
        private suspend fun datosIniciales(tipoMarcadorDao: TipoMarcadorDao, marcadorDao: MarcadorDao) {
            try {
                val tipos = listOf(
                    TipoMarcador(tituloTipoMarcador = "Restaurante"),
                    TipoMarcador(tituloTipoMarcador = "Supermercado"),
                    TipoMarcador(tituloTipoMarcador = "Playa"),
                    TipoMarcador(tituloTipoMarcador = "Biblioteca")
                )

                val tiposFromDb = tipoMarcadorDao.obtenerTodosTipos().firstOrNull() ?: emptyList()

                if (tiposFromDb.isEmpty()) {
                    tipos.forEach {
                        try {
                            tipoMarcadorDao.insertarTipoMarcador(it)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                val tiposFromDbUpdated = tipoMarcadorDao.obtenerTodosTipos().firstOrNull() ?: emptyList()

                val marcadoresFromDb = marcadorDao.obtenerTodosMarcadoresYTipos().firstOrNull() ?: emptyList()

                val marcadores = listOf(
                    Marcador(tituloMarcador = "Restaurante La Tegala", coordenadaX = 28.972511, coordenadaY = -13.661507, idTipoMarcador = tiposFromDbUpdated[0].idTipoMarcador),
                    Marcador(tituloMarcador = "El Barquillo", coordenadaX = 29.029535, coordenadaY = -13.519138, idTipoMarcador = tiposFromDbUpdated[0].idTipoMarcador),
                    Marcador(tituloMarcador = "Supermercado Spar", coordenadaX = 29.003576, coordenadaY = -13.534444, idTipoMarcador = tiposFromDbUpdated[1].idTipoMarcador),
                    Marcador(tituloMarcador = "Superdino", coordenadaX = 28.992539, coordenadaY = -13.544739, idTipoMarcador = tiposFromDbUpdated[1].idTipoMarcador),
                    Marcador(tituloMarcador = "Playa de Papagayo", coordenadaX = 28.931591, coordenadaY = -13.832378, idTipoMarcador = tiposFromDbUpdated[2].idTipoMarcador),
                    Marcador(tituloMarcador = "Playa Blanca", coordenadaX = 28.862619842252442, coordenadaY = -13.829885170935402, idTipoMarcador = tiposFromDbUpdated[2].idTipoMarcador),
                    Marcador(tituloMarcador = "Playa de Famara", coordenadaX = 29.11529884448325, coordenadaY = -13.557611212563845, idTipoMarcador = tiposFromDbUpdated[2].idTipoMarcador),
                    Marcador(tituloMarcador = "Biblioteca Municipal de Arrecife", coordenadaX = 28.963180, coordenadaY = -13.550132, idTipoMarcador = tiposFromDbUpdated[3].idTipoMarcador),
                    Marcador(tituloMarcador = "Biblioteca de San Bartolomé", coordenadaX = 28.961247, coordenadaY = -13.571539, idTipoMarcador = tiposFromDbUpdated[3].idTipoMarcador),
                    Marcador(tituloMarcador = "Biblioteca de Teguise", coordenadaX = 29.05992276261602, coordenadaY = -13.560779774412401, idTipoMarcador = tiposFromDbUpdated[3].idTipoMarcador),
                    Marcador(tituloMarcador = "Playa Francesa de La Graciosa", coordenadaX = 29.274952, coordenadaY = -13.506243, idTipoMarcador = tiposFromDbUpdated[2].idTipoMarcador),
                    Marcador(tituloMarcador = "Restaurante La Puerta Verde", coordenadaX = 29.146671, coordenadaY = -13.505749, idTipoMarcador = tiposFromDbUpdated[0].idTipoMarcador)
                )

                if (marcadoresFromDb.isEmpty()) {
                    marcadores.forEach {
                        try {
                            marcadorDao.insertarMarcador(it)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
