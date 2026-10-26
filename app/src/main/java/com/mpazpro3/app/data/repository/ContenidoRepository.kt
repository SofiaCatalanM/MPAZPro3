package com.mpazpro3.app.data.repository

import com.mpazpro3.app.data.dao.ContenidoDao
import com.mpazpro3.app.data.dao.ObjetivoDao
import com.mpazpro3.app.data.entity.Contenido
import com.mpazpro3.app.data.entity.ObjetivoAprendizaje

class ContenidoRepository(
    private val contenidoDao: ContenidoDao,
    private val objetivoDao: ObjetivoDao
) {
    suspend fun obtenerObjetivosPorAsignatura(asignatura: String): List<ObjetivoAprendizaje> {
        return objetivoDao.obtenerPorAsignatura(asignatura)
    }

    suspend fun obtenerContenidosPorObjetivo(objetivoCodigo: String): List<Contenido> {
        return contenidoDao.obtenerPorObjetivo(objetivoCodigo)
    }

    suspend fun guardarContenido(contenido: Contenido): Result<Unit> {
        return try {
            if (contenido.titulo.isBlank() || contenido.explicacion.isBlank()) {
                Result.failure(IllegalArgumentException("Titulo y explicacion son obligatorios"))
            } else {
                contenidoDao.insertar(contenido)
                Result.success(Unit)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
