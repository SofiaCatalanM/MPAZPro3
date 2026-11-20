package com.mpazpro3.app.data.repository

import com.mpazpro3.app.data.database.AppDatabase
import com.mpazpro3.app.data.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataInitializer(private val database: AppDatabase) {

    suspend fun initializeData() = withContext(Dispatchers.IO) {
        // Verificar si ya hay datos
        val asignaturasExistentes = database.asignaturaDao().obtenerTodas()
        if (asignaturasExistentes.isNotEmpty()) return@withContext

        // Insertar asignaturas
        val asignaturas = listOf(
            Asignatura("LENGUAJE", "Lenguaje y Comunicación"),
            Asignatura("MATEMATICA", "Matemática"),
            Asignatura("CIENCIAS", "Ciencias Naturales"),
            Asignatura("HISTORIA", "Historia y Ciencias Sociales")
        )
        asignaturas.forEach { database.asignaturaDao().insertar(it) }

        // Insertar objetivos de aprendizaje (ejemplos para Lenguaje)
        val objetivosLenguaje = listOf(
            ObjetivoAprendizaje(
                codigo = "OA01",
                descripcion = "Leer y familiarizarse con un amplio repertorio de literatura",
                asignaturaCodigo = "LENGUAJE",
                unidad = 1,
                ejeCurricular = "Lectura"
            ),
            ObjetivoAprendizaje(
                codigo = "OA02",
                descripcion = "Comprender textos orales y audiovisuales",
                asignaturaCodigo = "LENGUAJE",
                unidad = 1,
                ejeCurricular = "Comprensión Oral"
            )
        )
        objetivosLenguaje.forEach { database.objetivoDao().insertar(it) }

        // Insertar contenidos
        val contenidos = listOf(
            Contenido(
                titulo = "Cuentos Clásicos",
                explicacion = "Los cuentos clásicos son narraciones breves que han sido transmitidas de generación en generación...",
                ejemplo = "Ejemplo: Caperucita Roja, Los Tres Cerditos",
                objetivoCodigo = "OA01",
                aprobadoUtp = true
            ),
            Contenido(
                titulo = "Comprensión Lectora",
                explicacion = "La comprensión lectora es la capacidad de entender lo que se lee...",
                ejemplo = "Identificar ideas principales y secundarias",
                objetivoCodigo = "OA01",
                aprobadoUtp = true
            )
        )
        contenidos.forEach { database.contenidoDao().insertar(it) }

        // Insertar actividades
        val actividades = listOf(
            Actividad(
                pregunta = "¿Cuál es el personaje principal de Caperucita Roja?",
                alternativaA = "El lobo",
                alternativaB = "Caperucita",
                alternativaC = "La abuelita",
                alternativaD = "El cazador",
                respuestaCorrecta = "B",
                objetivoCodigo = "OA01",
                dificultad = "facil"
            ),
            Actividad(
                pregunta = "¿Qué lleva Caperucita en su canasta?",
                alternativaA = "Juguetes",
                alternativaB = "Libros",
                alternativaC = "Comida para su abuelita",
                alternativaD = "Ropa",
                respuestaCorrecta = "C",
                objetivoCodigo = "OA01",
                dificultad = "facil"
            )
        )
        actividades.forEach { database.actividadDao().insertar(it) }
    }
}