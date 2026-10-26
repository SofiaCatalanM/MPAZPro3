package com.mpazpro3.app.data.dao

import androidx.room.*
import com.mpazpro3.app.data.entity.Actividad

@Dao
interface ActividadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(actividad: Actividad)

    @Query("SELECT * FROM actividades WHERE objetivoCodigo = :objetivo")
    suspend fun obtenerPorObjetivo(objetivo: String): List<Actividad>

    @Query("SELECT * FROM actividades WHERE id = :id")
    suspend fun buscarPorId(id: Int): Actividad?
}
