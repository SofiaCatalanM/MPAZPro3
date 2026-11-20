package com.mpazpro3.app.data.dao

import androidx.room.*
import com.mpazpro3.app.data.entity.RespuestaEstudiante

@Dao
interface RespuestaDao {
    @Insert
    suspend fun insertar(respuesta: RespuestaEstudiante)

    @Query("SELECT * FROM respuestas_estudiante WHERE estudianteId = :estudianteId")
    suspend fun obtenerPorEstudiante(estudianteId: Int): List<RespuestaEstudiante>

    @Query("SELECT COUNT(*) FROM respuestas_estudiante WHERE estudianteId = :estudianteId AND esCorrecta = 1")
    suspend fun contarCorrectas(estudianteId: Int): Int

    @Query("SELECT COUNT(*) FROM respuestas_estudiante WHERE estudianteId = :estudianteId")
    suspend fun contarTotal(estudianteId: Int): Int
}
