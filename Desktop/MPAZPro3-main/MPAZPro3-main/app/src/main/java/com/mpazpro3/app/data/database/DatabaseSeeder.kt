package com.mpazpro3.app.data.database

import com.mpazpro3.app.data.entity.Contenido
import com.mpazpro3.app.data.entity.ObjetivoAprendizaje

object DatabaseSeeder {
    suspend fun seed(database: AppDatabase) {
        val objetivoDao = database.objetivoDao()
        val contenidoDao = database.contenidoDao()

        // MATEMATICA
        objetivoDao.insertar(
            ObjetivoAprendizaje(
                codigo = "MAT-OA-01",
                descripcion = "Resolver ecuaciones de primer grado",
                asignaturaCodigo = "MATEMATICA",
                unidad = 1,
                ejeCurricular = "Algebra"
            )
        )
        objetivoDao.insertar(
            ObjetivoAprendizaje(
                codigo = "MAT-OA-02",
                descripcion = "Calcular areas y perimetros de figuras basicas",
                asignaturaCodigo = "MATEMATICA",
                unidad = 2,
                ejeCurricular = "Geometria"
            )
        )

        contenidoDao.insertar(
            Contenido(
                titulo = "Guia de ecuaciones de primer grado",
                explicacion = "Una ecuacion de primer grado tiene la forma ax + b = 0. El objetivo es despejar la incognita x.",
                ejemplo = "2x + 6 = 0 entonces x = -3",
                objetivoCodigo = "MAT-OA-01",
                aprobadoUtp = true
            )
        )
        contenidoDao.insertar(
            Contenido(
                titulo = "Evaluacion de ecuaciones",
                explicacion = "Pon a prueba lo aprendido resolviendo 5 ecuaciones de primer grado.",
                ejemplo = "Resuelve 3x - 9 = 0",
                objetivoCodigo = "MAT-OA-01",
                aprobadoUtp = true
            )
        )
        contenidoDao.insertar(
            Contenido(
                titulo = "Video: areas y perimetros",
                explicacion = "Repaso visual del calculo de areas y perimetros en rectangulos y triangulos.",
                ejemplo = "Area del rectangulo = base por altura",
                objetivoCodigo = "MAT-OA-02",
                aprobadoUtp = true
            )
        )

        // CIENCIAS
        objetivoDao.insertar(
            ObjetivoAprendizaje(
                codigo = "CIE-OA-01",
                descripcion = "Describir el ciclo del agua y sus estados",
                asignaturaCodigo = "CIENCIAS",
                unidad = 1,
                ejeCurricular = "Ciencias de la Tierra"
            )
        )
        objetivoDao.insertar(
            ObjetivoAprendizaje(
                codigo = "CIE-OA-02",
                descripcion = "Identificar los planetas y los movimientos de la Tierra",
                asignaturaCodigo = "CIENCIAS",
                unidad = 2,
                ejeCurricular = "Astronomia"
            )
        )

        contenidoDao.insertar(
            Contenido(
                titulo = "Lectura: estados del agua",
                explicacion = "El agua cambia entre solido, liquido y gaseoso segun la temperatura, formando el ciclo del agua.",
                ejemplo = "La evaporacion convierte el agua liquida en vapor",
                objetivoCodigo = "CIE-OA-01",
                aprobadoUtp = true
            )
        )
        contenidoDao.insertar(
            Contenido(
                titulo = "Quiz del sistema solar",
                explicacion = "Evalua tu conocimiento sobre los planetas y sus caracteristicas.",
                ejemplo = "Cual es el planeta mas cercano al Sol",
                objetivoCodigo = "CIE-OA-02",
                aprobadoUtp = true
            )
        )

        // HISTORIA
        objetivoDao.insertar(
            ObjetivoAprendizaje(
                codigo = "HIS-OA-01",
                descripcion = "Reconocer los pueblos originarios de Chile",
                asignaturaCodigo = "HISTORIA",
                unidad = 1,
                ejeCurricular = "Identidad"
            )
        )
        objetivoDao.insertar(
            ObjetivoAprendizaje(
                codigo = "HIS-OA-02",
                descripcion = "Comprender el proceso de independencia de Chile",
                asignaturaCodigo = "HISTORIA",
                unidad = 2,
                ejeCurricular = "Historia de Chile"
            )
        )

        contenidoDao.insertar(
            Contenido(
                titulo = "Mapa de pueblos originarios",
                explicacion = "Los principales pueblos originarios son Mapuche, Aymara, Rapa Nui y Kaweskar, cada uno con su territorio.",
                ejemplo = "El pueblo Mapuche habito principalmente en el sur de Chile",
                objetivoCodigo = "HIS-OA-01",
                aprobadoUtp = true
            )
        )
        contenidoDao.insertar(
            Contenido(
                titulo = "Evaluacion: independencia",
                explicacion = "Actividad de evaluacion sobre las etapas del proceso de independencia.",
                ejemplo = "La primera junta nacional se formo en 1810",
                objetivoCodigo = "HIS-OA-02",
                aprobadoUtp = true
            )
        )
    }
}