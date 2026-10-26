package com.mpazpro3.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "respuestas_estudiante")
data class RespuestaEstudiante(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val estudianteId: Int,
    val actividadId: Int,
    val respuestaSeleccionada: String,
    val esCorrecta: Boolean,
    val fecha: Long = System.currentTimeMillis()
)
