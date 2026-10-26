package com.mpazpro3.app.data.repository

import com.mpazpro3.app.data.dao.ActividadDao
import com.mpazpro3.app.data.dao.EvaluacionDao
import com.mpazpro3.app.data.dao.RespuestaDao
import com.mpazpro3.app.data.entity.Actividad
import com.mpazpro3.app.data.entity.Evaluacion
import com.mpazpro3.app.data.entity.RespuestaEstudiante

class EvaluacionRepository(
    private val actividadDao: ActividadDao,
    private val respuestaDao: RespuestaDao,
    private val evaluacionDao: EvaluacionDao
) {
    suspend fun obtenerActividades(objetivoCodigo: String): List<Actividad> {
        return actividadDao.obtenerPorObjetivo(objetivoCodigo)
    }

    suspend fun registrarRespuesta(
        estudianteId: Int,
        actividadId: Int,
        respuesta: String
    ): Result<Boolean> {
        return try {
            val actividad = actividadDao.buscarPorId(actividadId)
                ?: return Result.failure(IllegalStateException("Actividad no encontrada"))
            val esCorrecta = respuesta == actividad.respuestaCorrecta
            respuestaDao.insertar(
                RespuestaEstudiante(
                    estudianteId = estudianteId,
                    actividadId = actividadId,
                    respuestaSeleccionada = respuesta,
                    esCorrecta = esCorrecta
                )
            )
            Result.success(esCorrecta)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun finalizarEvaluacion(
        estudianteId: Int,
        objetivoCodigo: String,
        maxIntentos: Int
    ): Result<Evaluacion> {
        return try {
            val ultimoIntento = evaluacionDao.obtenerUltimoIntento(estudianteId, objetivoCodigo) ?: 0
            if (ultimoIntento >= maxIntentos) {
                return Result.failure(IllegalStateException("Limite de intentos alcanzado"))
            }
            val correctas = respuestaDao.contarCorrectas(estudianteId)
            val total = respuestaDao.contarTotal(estudianteId)
            val evaluacion = Evaluacion(
                estudianteId = estudianteId,
                objetivoCodigo = objetivoCodigo,
                puntaje = correctas,
                total = total,
                intento = ultimoIntento + 1
            )
            evaluacionDao.insertar(evaluacion)
            Result.success(evaluacion)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerInforme(estudianteId: Int): Map<String, Any> {
        val correctas = respuestaDao.contarCorrectas(estudianteId)
        val total = respuestaDao.contarTotal(estudianteId)
        val porcentaje = if (total > 0) (correctas * 100) / total else 0
        return mapOf(
            "correctas" to correctas,
            "total" to total,
            "porcentaje" to porcentaje
        )
    }
}
