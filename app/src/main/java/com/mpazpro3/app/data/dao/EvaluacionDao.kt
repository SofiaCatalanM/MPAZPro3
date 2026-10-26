package com.mpazpro3.app.data.dao

import androidx.room.*
import com.mpazpro3.app.data.entity.Evaluacion

@Dao
interface EvaluacionDao {
    @Insert
    suspend fun insertar(evaluacion: Evaluacion)

    @Query("SELECT * FROM evaluaciones WHERE estudianteId = :estudianteId")
    suspend fun obtenerPorEstudiante(estudianteId: Int): List<Evaluacion>

    @Query("SELECT MAX(intento) FROM evaluaciones WHERE estudianteId = :estudianteId AND objetivoCodigo = :objetivo")
    suspend fun obtenerUltimoIntento(estudianteId: Int, objetivo: String): Int?
}
