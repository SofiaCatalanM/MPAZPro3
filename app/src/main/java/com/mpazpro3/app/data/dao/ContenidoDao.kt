package com.mpazpro3.app.data.dao

import androidx.room.*
import com.mpazpro3.app.data.entity.Contenido

@Dao
interface ContenidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(contenido: Contenido)

    @Query("SELECT * FROM contenidos WHERE objetivoCodigo = :objetivo")
    suspend fun obtenerPorObjetivo(objetivo: String): List<Contenido>

    @Query("SELECT * FROM contenidos WHERE aprobadoUtp = 1")
    suspend fun obtenerAprobados(): List<Contenido>
}
