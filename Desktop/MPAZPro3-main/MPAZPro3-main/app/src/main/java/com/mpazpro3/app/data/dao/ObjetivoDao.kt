package com.mpazpro3.app.data.dao

import androidx.room.*
import com.mpazpro3.app.data.entity.ObjetivoAprendizaje

@Dao
interface ObjetivoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(objetivo: ObjetivoAprendizaje)

    @Query("SELECT * FROM objetivos_aprendizaje WHERE asignaturaCodigo = :asignatura")
    suspend fun obtenerPorAsignatura(asignatura: String): List<ObjetivoAprendizaje>

    @Query("SELECT * FROM objetivos_aprendizaje WHERE codigo = :codigo")
    suspend fun buscarPorCodigo(codigo: String): ObjetivoAprendizaje?
}
