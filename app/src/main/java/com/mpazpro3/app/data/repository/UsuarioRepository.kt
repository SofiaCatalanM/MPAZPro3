package com.mpazpro3.app.data.repository

import com.mpazpro3.app.data.dao.UsuarioDao
import com.mpazpro3.app.data.entity.Usuario

class UsuarioRepository(private val dao: UsuarioDao) {

    suspend fun registrar(nombre: String, rol: String, curso: String? = null): Result<Usuario> {
        return try {
            if (nombre.isBlank()) {
                Result.failure(IllegalArgumentException("El nombre no puede estar vacio"))
            } else if (rol !in listOf("estudiante", "docente", "utp")) {
                Result.failure(IllegalArgumentException("Rol invalido"))
            } else {
                val usuario = Usuario(nombre = nombre, rol = rol, curso = curso)
                dao.insertar(usuario)
                Result.success(usuario)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun buscarPorId(id: Int): Usuario? = dao.buscarPorId(id)

    suspend fun obtenerTodos(): List<Usuario> = dao.obtenerTodos()
}
