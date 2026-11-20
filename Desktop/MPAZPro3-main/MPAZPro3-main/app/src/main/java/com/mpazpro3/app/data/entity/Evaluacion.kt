package com.mpazpro3.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "evaluaciones")
data class Evaluacion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val estudianteId: Int,
    val objetivoCodigo: String,
    val puntaje: Int,
    val total: Int,
    val fecha: Long = System.currentTimeMillis(),
    val intento: Int
)
