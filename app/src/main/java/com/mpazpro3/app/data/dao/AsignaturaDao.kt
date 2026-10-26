package com.mpazpro3.app.data.dao

import androidx.room.*
import com.mpazpro3.app.data.entity.Asignatura

@Dao
interface AsignaturaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(asignatura: Asignatura)

    @Query("SELECT * FROM asignaturas")
    suspend fun obtenerTodas(): List<Asignatura>

    @Query("SELECT * FROM asignaturas WHERE codigo = :codigo")
    suspend fun buscarPorCodigo(codigo: String): Asignatura?
}
