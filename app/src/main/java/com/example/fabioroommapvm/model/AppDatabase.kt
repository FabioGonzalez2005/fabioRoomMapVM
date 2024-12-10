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

    }
}
