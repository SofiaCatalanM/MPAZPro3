package com.mpazpro3.app.data.dao

import androidx.room.*
import com.mpazpro3.app.data.entity.Usuario

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun buscarPorId(id: Int): Usuario?

    @Query("SELECT * FROM usuarios WHERE rol = :rol")
    suspend fun buscarPorRol(rol: String): List<Usuario>

    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodos(): List<Usuario>
}
