package com.mpazpro3.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mpazpro3.app.data.dao.*
import com.mpazpro3.app.data.entity.*

@Database(
    entities = [
        Usuario::class,
        Asignatura::class,
        ObjetivoAprendizaje::class,
        Contenido::class,
        Actividad::class,
        RespuestaEstudiante::class,
        Evaluacion::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun asignaturaDao(): AsignaturaDao
    abstract fun objetivoDao(): ObjetivoDao
    abstract fun contenidoDao(): ContenidoDao
    abstract fun actividadDao(): ActividadDao
    abstract fun respuestaDao(): RespuestaDao
    abstract fun evaluacionDao(): EvaluacionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obtener(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mpazpro3_database"
                ).fallbackToDestructiveMigration()
                 .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
